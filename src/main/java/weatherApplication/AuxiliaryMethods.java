package weatherApplication;

public class AuxiliaryMethods {

    public static String setFirstCapitalLetter(String stringToConvert) {
        String firstUpperCase = "";
        if (!stringToConvert.equals("")) {
            firstUpperCase = stringToConvert.substring(0, 1).toUpperCase()
                    + stringToConvert.substring(1).toLowerCase();
        }
        return firstUpperCase;
    }
}
