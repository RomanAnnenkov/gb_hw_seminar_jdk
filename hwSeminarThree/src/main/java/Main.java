import calculator.Calculator;
import pair.Pair;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw seminar three");

        //1. Написать класс Калькулятор (необобщенный), который содержит обобщенные статические методы:
        // sum(), multiply(), divide(), subtract().
        // Параметры этих методов – два числа разного типа, над которыми должна быть произведена операция.
        System.out.println(Calculator.sum(BigDecimal.valueOf(1L), 0.2));
        System.out.println(Calculator.subtract(20.1, 2));
        System.out.println(Calculator.multiply(1f, 0.2));
        System.out.println(Calculator.divide((byte) 1, 0.2));

        //2. Напишите обобщенный метод compareArrays(), который принимает два массива и возвращает true, если
        // они одинаковые, и false в противном случае. Массивы могут быть любого типа данных, но должны иметь
        // одинаковую длину и содержать элементы одного типа.
        Number[] arr1 = new Number[]{1, 0.1, 2f};
        Number[] arr2 = new Number[]{4, 0.4, 10f};
        System.out.println(compareArrays(arr1, arr2));

        //3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
        // Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих
        // пары, а также переопределение метода toString(), возвращающее строковое представление пары.
        Pair<Integer, String> pair = new Pair<>(1, "test");
        System.out.println(pair.getFirst());
        System.out.println(pair.getSecond());
        System.out.println(pair);
    }

    public static <T> boolean compareArrays(T[] arr1, T[] arr2) {
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (!arr1[i].getClass().getName().equals(arr2[i].getClass().getName())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
