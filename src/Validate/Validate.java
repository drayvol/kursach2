package Validate;

public class Validate {
    public static String validateString(String value){
        if (value == null || value.isBlank() || value.isEmpty()){ throw new IllegalArgumentException("Данные введены неверно");}
        return value;
    }
    public static String validateInfo(String value, String substitution){
        if (value == null || value.isBlank() || value.isEmpty()){ return substitution;}
        return value;
    }
    public static Boolean validateBoolean (Boolean value){return value != null && value;}

    public static Integer validateTypeOfTask (Integer value){
        if (value != 0 && value != 1) throw new IllegalArgumentException("Данные введены неверно");
        return value;
    }
    public static Integer validatePeriodicity (Integer value){
        if (value != 0 && value != 1 && value != 2 && value != 3 && value != 4 ) throw new IllegalArgumentException("Данные введены неверно");
        return value;
    }
}