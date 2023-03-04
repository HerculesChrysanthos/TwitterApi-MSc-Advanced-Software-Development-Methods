package com.twitter.util;

import java.time.LocalDateTime;

public class SystemDateTimeStub {

    public static void setStub(LocalDateTime stub) {
        SystemDateTime.setStub(stub);
    }

    public static void reset() {
        SystemDateTime.removeStub();
    }

}
