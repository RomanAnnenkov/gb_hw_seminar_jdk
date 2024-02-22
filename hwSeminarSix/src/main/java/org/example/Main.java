package org.example;

import org.apache.commons.math3.stat.Frequency;

public class Main {
    public static void main(String[] args) {
        System.out.println("hw seminar six");
        //В качестве задачи предлагаю вам реализовать код для демонстрации парадокса
        //Монти Холла (Парадокс Монти Холла — Википедия ) и наглядно убедиться
        //в верности парадокса (запустить игру в цикле на 1000 и вывести итоговый счет).
        //Необходимо:
        //Создать свой Java Maven или Gradle проект;
        //Подключите зависимость lombok и возможно какую то математическую библиотеку (напр. commons-math3)
        //Самостоятельно реализовать прикладную задачу;
        //Сохранить результат игр в одну из коллекций или в какой то библиотечный класс
        //Вывести на экран статистику по победам и поражениям

        MontyHallGame game = new MontyHallGame();
        Frequency frequencyWithChange = new Frequency();
        Frequency frequencyWithoutChange = new Frequency();

        for (int i = 0; i < 1000; i++) {
            frequencyWithChange.addValue(game.play(true));
            frequencyWithoutChange.addValue(game.play(false));
        }
        System.out.println("Win frequency with change choice :");
        System.out.println(frequencyWithChange);
        System.out.println("Win frequency without change choice :");
        System.out.println(frequencyWithoutChange);

    }
}