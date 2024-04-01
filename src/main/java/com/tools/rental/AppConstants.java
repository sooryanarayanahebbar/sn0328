package com.tools.rental;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface AppConstants {

    String fullDateTimeFormatterPattern = "E MMM dd HH:mm:ss z uuuu";
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( AppConstants.fullDateTimeFormatterPattern )
            .withLocale( Locale.US );
    String dateFormat = "MM/dd/yy";
    SimpleDateFormat appDateFormatter = new SimpleDateFormat("MM/dd/yy", Locale.US);

    String dateTimeFormatterPattern = "MM/dd/uu";
    String laborDay = "1st Monday fo Sept";
    String IndependenceDay = "July 4th";
}
