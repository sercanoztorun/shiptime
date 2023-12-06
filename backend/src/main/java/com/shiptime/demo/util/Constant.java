package com.shiptime.demo.util;

import java.time.format.DateTimeFormatter;

public final class Constant {
    private Constant(){}
    public static final String BASE_URL = "https://restapi.appspaces.ca/rest";
    public static final String SHIPPING_LOCATION = "FrontDoor";
    public static final String PACKAGE_TYPE = "PACKAGE";
    public static final String UNIT_OF_MEASUREMENT = "METRIC";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
}
