package weatherApplication;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class AuxiliaryMethods {

    public static String setFirstCapitalLetter(String stringToConvert) {
        String firstUpperCase = "";
        if (!stringToConvert.equals("")) {
            firstUpperCase = stringToConvert.substring(0, 1).toUpperCase()
                    + stringToConvert.substring(1).toLowerCase();
        }
        return firstUpperCase;
    }

    public static String getNameDay(String date) {
        date = date.replace("-", "");
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(4, 6));
        int day = Integer.parseInt(date.substring(6, 8));

        LocalDate localDate = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        String stringDayOfWeek = dayOfWeek.toString();
        return setFirstCapitalLetter(stringDayOfWeek);
    }
}
