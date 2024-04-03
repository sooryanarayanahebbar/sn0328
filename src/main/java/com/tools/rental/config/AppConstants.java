package com.tools.rental.config;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public interface AppConstants {


    String fullDateTimeFormatterPattern = "E MMM dd HH:mm:ss z uuuu";
    static SimpleDateFormat appDateYFormatter = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( AppConstants.fullDateTimeFormatterPattern )
            .withLocale( Locale.US );
    String dateFormat = "MM/dd/yy";
    SimpleDateFormat appDateFormatter = new SimpleDateFormat("MM/dd/yy", Locale.US);

    String dateTimeFormatterPattern = "MM/dd/uu";
    String laborDay = "1st Monday fo Sept";
    String IndependenceDay = "July 4th";
    
    String APP_RESPONSE_MESSAGE_SUCCESS = "SUCCESS";
    String APP_RESPONSE_MESSAGE_VALIDATION_ERROR = "VALIDATION ERROR(S)";


}
