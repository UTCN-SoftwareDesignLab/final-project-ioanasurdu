package project;

import java.time.format.DateTimeFormatter;

public class Constants {
    public static DateTimeFormatter getDateTimeFormat(){
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

}
