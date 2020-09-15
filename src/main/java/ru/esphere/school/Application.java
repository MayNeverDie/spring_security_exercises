package ru.esphere.school;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.testcontainers.containers.GenericContainer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Slf4j
@EnableSwagger2
@SpringBootApplication
@RequiredArgsConstructor
public class Application {
    private static final String MINIO_DOCKER_IMAGE = "minio/minio";

    private static final String MINIO_ACCESS_KEY = "minio";
    private static final String MINIO_SECRET_KEY = "minio123";
    private static final GenericContainer MINIO_CONTAINER;

    static {
        MINIO_CONTAINER = new MinioContainer(MINIO_DOCKER_IMAGE)
                .withMinioAccessKey(MINIO_ACCESS_KEY)
                .withMinioSecretKey(MINIO_SECRET_KEY);
        MINIO_CONTAINER.start();

        try {
            Runtime rt = Runtime.getRuntime();
            //Process pr = rt.exec("cmd /c dir");
            Process pr = rt.exec("docker ps");

            BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

            String line = null;

            System.out.println("Printing cmd lines:");
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }

            int exitVal = pr.waitFor();
            System.out.println("Exited with error code " + exitVal);


        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void appIsReady() {
        log.info("Application started successfully!");
    }
}
