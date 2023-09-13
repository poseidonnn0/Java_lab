// Лабораторная работа №1 по Java  Кондратьев Виталий

import java.util.Scanner;
import static java.lang.Integer.MAX_VALUE;

public class Main
{
    public static void main(String[] args)
    {
        Scanner inNumber = new Scanner(System.in);
        System.out.printf("Введите номер задачи, решение которой хотите проверить: ");
        int num = inNumber.nextInt();
        switch (num)
        {
            case 1:
                task_1();
                break;
            case 2:
                task_2();
                break;
            case 3:
                task_3();
                break;
            case 4:
                task_4();
                break;
            case 5:
                task_5();
                break;
            default:
                System.out.printf("Такого номера задачи не существует!");
                break;

        }
    }
    public static void task_1() // Сиракузская последовательность
    {
        Scanner number = new Scanner(System.in);
        System.out.printf("Введите натуральное число для Сиракузской последовательности: ");
        int n = number.nextInt();
        if (n>0)
        {
            int count = 0; // Счётчик последовательности
            while (n!=1)
            {
                if (n % 2 == 0)
                {
                    n /= 2;
                    count++;
                }
                else
                {
                    n = 3*n + 1;
                    count++;
                }
            }
            System.out.printf("Количество шагов в Сиракузской последовательности: " + count);
        }
        else
        {
            System.out.printf("Натуральное число введено неверно!");
        }

    }
    public static void task_2() // сумма ряда
    {
        Scanner number = new Scanner(System.in);
        System.out.printf("Введите количество натуральных чисел: ");
        int n = number.nextInt();
        if (n > 0)
        {
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
            {
                arr[i] = number.nextInt();
            }
            double res = 0;
            for (int i = 0; i < n; i++)
            {
                res += arr[i] * Math.pow(-1, i);
            }
            System.out.println(res);
        }
        else
        {
            System.out.printf("Натуральное число введено неверно!");
        }
    }
    public static void task_3() // ищем клад
    {
        Scanner num = new Scanner(System.in);
        System.out.printf("Введите координаты клада: ");
        int Treasure_X = num.nextInt(); // координаты x
        int Treasure_Y = num.nextInt(); // координаты y
        String str = "";
        int step;
        int x = 0, y = 0;
        int count = 0;
        boolean flag = true;
        while (true)
        {
            str = num.next();
            switch (str) {
                case "стоп":
                    flag = false;
                    break;
                case "север":
                    step = num.nextInt();
                    y += step;
                    if (flag) count++;
                    if (x == Treasure_X && y == Treasure_Y) flag = false;
                    continue;
                case "запад":
                    step = num.nextInt();
                    x -= step;
                    if (flag) count++;
                    if (x == Treasure_X && y == Treasure_Y) flag = false;
                    continue;
                case "восток":
                    step = num.nextInt();
                    x += step;
                    if (flag) count++;
                    if (x == Treasure_X && y == Treasure_Y) flag = false;
                    continue;
                case "юг":
                    step = num.nextInt();
                    y -= step;
                    if (flag) count++;
                    if (x == Treasure_X && y == Treasure_Y) flag = false;
                    continue;
                default:
                    System.out.printf("Такой команды не предусмотрено!");
                    break;
            }
            System.out.printf("Количество шагов до клада " + count);
        }
    }
    public static void task_4() // логистический максимин
    {
        {
            Scanner num = new Scanner(System.in);
            System.out.printf("Введите количество дорог: ");
            int NumberOfRoad = num.nextInt();
            int NumberOfTunnels;
            int CurrentTunnel;
            int CurrentRoad = 0;
            int[] MinT = new int[NumberOfRoad];
            for (int i = 0; i < NumberOfRoad; i++) // Количество дорог
            {
                int LocalMin = MAX_VALUE;
                System.out.printf("Введите количество туннелей для дороги: ");
                NumberOfTunnels = num.nextInt();
                for (int j = 0; j < NumberOfTunnels; j++) // Количество туннелей на дороге
                {
                    System.out.printf("Введите высоту туннеля: ");
                    CurrentTunnel = num.nextInt(); // вводим высоту туннеля
                    if (CurrentTunnel < LocalMin)
                    {
                        LocalMin = CurrentTunnel; // проверяем макс.высоту туннеля
                    }
                }
                MinT[i] = LocalMin;
            }
            int result = 0;
            for (int i = 0; i < NumberOfRoad; i++)
            {
                if (MinT[i] > result)
                {
                    result = MinT[i];
                    CurrentRoad = i++;
                }
            }
            System.out.println(CurrentRoad + " " + result);
        }
    }

    public static void task_5()
    {
        Scanner num = new Scanner(System.in);
        System.out.print("Введите трехзначное число: ");
        int number = num.nextInt();

        String string = String.valueOf(number);

        char[] array = string.toCharArray();

        if( ((array[0] + array[1] + array[2]) % 2) == 0
                && ((array[0] * array[1] * array[2]) % 2) == 0)
        {
            System.out.println("Число дважды четное");
        }
        else
        {
            System.out.println("Число не дважды нечетное");
        }
        /* // 2 способ
        int ones = 0; int tens = 0; int hundreds = 0;
        int N = num.nextInt();
        ones = N % 10;
        tens = N % 100 / 10;
        hundreds = N / 100;
        if (((ones + tens + hundreds) % 2 == 0) && ((ones * tens * hundreds) % 2 == 0))
        {
            System.out.println("Число дважды четное");
        }
        else
        {
            System.out.println("Число не дважды нечетное");
        }*/
    }
}

