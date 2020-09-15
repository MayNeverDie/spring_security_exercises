package ru.esphere.school;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.HttpWaitStrategy;

import java.time.Duration;

public class MinioContainer<SELF extends MinioContainer<SELF>> extends GenericContainer<SELF> {

    private static final int DEFAULT_PORT = 9000;
    private static final String IMAGE = "minio/minio";
    private static final String DEFAULT_TAG = "edge";

    private static final String MINIO_ACCESS_KEY = "MINIO_ACCESS_KEY";
    private static final String MINIO_SECRET_KEY = "MINIO_SECRET_KEY";

    private static final String DEFAULT_STORAGE_DIRECTORY = "/data";
    private static final String HEALTH_ENDPOINT = "/minio/health/ready";

    private String minioAccessKey = "test";
    private String minioSecretKey = "test";

    public MinioContainer() {
        this(IMAGE + ":" + DEFAULT_TAG);
    }

    public MinioContainer(final String dockerImageName) {
        //super(dockerImageName == null ? IMAGE + ":" + DEFAULT_TAG : dockerImageName);
        super(dockerImageName);
        this.waitStrategy = new HttpWaitStrategy()
                .forPort(DEFAULT_PORT)
                .forPath(HEALTH_ENDPOINT)
                .withStartupTimeout(Duration.ofMinutes(2));
        this.setCommand("server", DEFAULT_STORAGE_DIRECTORY);
    }

    @Override
    protected void configure() {
        addExposedPort(DEFAULT_PORT);
        addEnv(MINIO_ACCESS_KEY, minioAccessKey);
        addEnv(MINIO_SECRET_KEY, minioSecretKey);
    }

    public SELF withMinioAccessKey(final String minioAccessKey) {
        this.minioAccessKey = minioAccessKey;
        return self();
    }

    public SELF withMinioSecretKey(final String minioSecretKey) {
        this.minioSecretKey = minioSecretKey;
        return self();
    }
}
