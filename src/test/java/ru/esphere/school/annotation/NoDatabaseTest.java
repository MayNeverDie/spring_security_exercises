package ru.esphere.school.annotation;

import org.springframework.boot.test.context.SpringBootTest;
import ru.esphere.school.Application;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = Application.class)
public @interface NoDatabaseTest {
}
