package ru.esphere.school.constants.internal;

import ru.esphere.school.tools.annotations.Messages;

@Messages("[LAW-%03d] ")
final class InfoMessages {

    public static final String GET_INFO = "Get info for {}";
    public static final String GOT_INFO = "Got info for {} -> {}";
    public static final String GOT_ERROR = "Error got {}";

    private InfoMessages() {
        throw new UnsupportedOperationException();
    }
}
