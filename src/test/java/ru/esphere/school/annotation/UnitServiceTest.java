package ru.esphere.school.annotation;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
@Retention(RetentionPolicy.RUNTIME)
public @interface UnitServiceTest {
}
