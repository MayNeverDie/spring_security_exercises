package ru.esphere.school.tools.messages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.esphere.school.constants.InfoMessages;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.esphere.school.constants.InfoMessages.GET_INFO;
import static ru.esphere.school.constants.InfoMessages.GOT_INFO;


@DisplayName("Тест процессора аннотации Messages")
public class InfoMessagesTest {

    @Test
    @DisplayName("Проверка сообщений GET_INFO и GOT_INFO")
    void test() {

        assertThat(GET_INFO)
                .isEqualTo("[LAW-001] Get info for {}");

        assertThat(GOT_INFO)
                .isEqualTo("[LAW-002] Got info for {} -> {}");

        assertThat(InfoMessages.GOT_ERROR)
                .isEqualTo("[LAW-003] Error got {}");

    }

}
