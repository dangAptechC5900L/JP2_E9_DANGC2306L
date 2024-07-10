package Validate;

public class Validation {
    public static boolean isIdProduct(String id){
        String patternIdProduct="^(MS|NE|SE)[0-9]{6}$";
        return  id.matches(patternIdProduct);
    }

    public static boolean isNameProduct(String name){
        String patternNameProduct = "^[a-zA-Z\\s]+$";
        return name.matches(patternNameProduct);
    }

    public static boolean isNameCustomer(String nameCustomer){
        String patternNameCustomer="^[a-zA-Z\\s]{3,50}$";
        return nameCustomer.matches(patternNameCustomer);
    }

    public static boolean isIdOrder(String idOrder){
        String patternIdOrder="^(OPRM)[0-9]{8}$";
        return idOrder.matches(patternIdOrder);
    }






}
