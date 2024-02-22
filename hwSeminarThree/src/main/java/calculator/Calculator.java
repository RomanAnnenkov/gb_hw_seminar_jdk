package calculator;

public class Calculator {

    public static <T extends Number> double sum(T arg1, T arg2) {
        return arg1.doubleValue() + arg2.doubleValue();
    }

    public static <T extends Number> double subtract(T arg1, T arg2) {
        return arg1.doubleValue() - arg2.doubleValue();
    }

    public static <T extends Number> double multiply(T arg1, T arg2) {
        return arg1.doubleValue() * arg2.doubleValue();
    }

    public static <T extends Number> double divide(T arg1, T arg2) {
        return arg1.doubleValue() / arg2.doubleValue();
    }


}
