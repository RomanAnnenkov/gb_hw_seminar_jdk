import calculator.Calculator;

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

        //3. Напишите обобщенный класс Pair, который представляет собой пару значений разного типа.
        // Класс должен иметь методы getFirst(), getSecond() для получения значений каждого из составляющих
        // пары, а также переопределение метода toString(), возвращающее строковое представление пары.
    }
}
