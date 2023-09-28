// Лабораторная работа №2 по Java  Кондратьев Виталий

import java.util.Scanner;
import java.util.Arrays;
public class Main
{
    public static void main(String[] args)
    {
        Scanner inNumber = new Scanner(System.in);
        System.out.printf("Введите номер задачи, решение которой хотите проверить: ");
        int num = inNumber.nextInt();
        int[] arr1 = new int[] {2,13,244,2224};
        int[] arr2 = new int[] {-899,-29,-11,0,1,24,42424,425253};
        int[] arr3 = new int[] {-11,-9,-3,0,4,14,44,453, -5, -787, 45, 46, 77}; // {-11, -4, -6};
        int[] arr5 = new int[] {3, 5, 42, 64, 1, 9, 4, 7, 31};
        int[][] arr = new int[][]
                {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                };
        int[][] arr7 = new int[][]
                {
                        {1,2,43,4},
                        {5,6,74,8},
                        {9,140,11,12},
                };
        switch (num)
        {
            case 1:
                //task1("abbbcnffff");
                task1("abacabbbadabacababcdfea"); //abbbcnffff
                break;
            case 2:
                task2(arr1, arr2);
                break;
            case 3:
                task3(arr3);
                break;
            case 4:
                task4(arr);
                break;
            case 5:
                task5(arr5,35);
                break;
            case 6:
                task6(arr);
                break;
            case 7:
                task7(arr7);
                break;
            case 8:
                task8(arr);
                break;
            default:
                System.out.printf("Такого номера задачи не существует!");
                break;

        }
    }
    public static void task1(String str)
    {
        if (str == null || str.isEmpty())
        {
            System.out.println("Пустая строка или строка не обнаружена!");
        }
        else
        {
            int sLength = str.length();
            StringBuilder Symbols = new StringBuilder();
            char[] CurSymb = new char[sLength];
            int i = 0; int j = 0; int result = 0;
            int maxLength = 0, start = 0;
            for (int z = 0; z < sLength; z++)
            {
                CurSymb[z] = str.charAt(z); // заполняем строку в массив чара(по каждому символу)
            }
            while (j < sLength && i < sLength)
            {
                if (Symbols.toString().indexOf(CurSymb[j]) == -1)
                {
                    Symbols.append(CurSymb[j]);
                    j++;
                    result = Math.max(result, j - i);
                    if (j-i > maxLength)
                    {
                        maxLength = j-i;
                        start = i;
                    }
                }
                else
                {
                    Symbols = new StringBuilder(Symbols.toString().replace(CurSymb[i], ' '));
                    i++;
                }
            }
            System.out.println("Количество повторяющихся символов = " + result + " , где сами символы: " + str.substring(start, start+maxLength));
        }
    }
    public static void task2(int[] arr1,int[] arr2)
    {
        int length1 = arr1.length;
        int length2 = arr2.length;
        int i = 0; int j = 0; int k = 0;
        int[] mergedArr = new int[length1+length2];

        while (i < length1 && j < length2)
        {
            if (arr1[i] <= arr2[j])
            {
                mergedArr[k++] = arr1[i++];
            }
            else
            {
                mergedArr[k++] = arr2[j++];
            }
        }
        while (i < length1)
        {
            mergedArr[k++] = arr1[i++];
        }
        while (j < length2)
        {
            mergedArr[k++] = arr2[j++];
        }

        for (int l=0; l < length1+length2; l++)
        {
            System.out.println(mergedArr[l]);
        }
    }
    public static void task3(int[] arr)
    { // Алгоритм Кадане
        int res = 0;
        int CurrMax = 0;
        int MaxNumber = Arrays.stream(arr).max().getAsInt();
        for (int i : arr)
        {
            CurrMax += i;
            // если максимальная сумма отрицательна, устанавливаем ее в 0
            // (что представляет пустой подмассив)
            CurrMax = Math.max(CurrMax, 0);
            res = Math.max(res, CurrMax);
        }
        res = MaxNumber < 0 ? MaxNumber : res;
        System.out.println(res);
    }
    public static void task4(int[][] arr)
    {
        int[][] RotatedArr = new int[arr[0].length][arr.length]; // поворот на 90 градусов вправо
        for (int i = 0; arr.length > i; ++i)
        {
            for (int j = 0; arr[0].length > j; j++)
            {
                RotatedArr[j][arr.length - i - 1] = arr[i][j]; // сам поворот
            }
        }
        for (int i = 0; RotatedArr.length > i; ++i)
        {
            for (int j = 0; RotatedArr[i].length > j; j++)
            {
                System.out.print(RotatedArr[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static void task8(int[][] arr)
    { // поворот на 90 градусов влево
        int[][] RotatedArr = new int[arr[0].length][arr.length]; // поворот на 90 градусов вправо
        for (int i = 0; arr.length > i; ++i)
        {
            for (int j = 0; arr[0].length > j; j++)
            {
                RotatedArr[j][i] = arr[i][arr[0].length - j - 1]; // сам поворот влево
            }
        }
        for (int i = 0; RotatedArr.length > i; ++i)
        {
            for (int j = 0; RotatedArr[i].length > j; j++)
            {
                System.out.print(RotatedArr[i][j]+ " ");
            }
            System.out.println();
        }
    }
    public static void task5(int[] arr,int target) {
        int res [] = new int [2];
        for (int i = 0; arr.length > i; i++)
        {
            for (int j = i+1; arr.length > j; j++)
            {
                if (arr[i] + arr[j] == target)
                {
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        if (res != null)
        {
            System.out.println(arr[res[0]] + " " + arr[res[1]]);
        }
        else
        {
            System.out.println("null");
        }
    }
    public static void task6(int[][] arr)
    {
        int sumElements = 0;
        for (int i = 0; arr.length > i; i++)
        {
            for (int j = 0; arr[i].length > j; j++)
            {
                sumElements += arr[i][j];
            }
        }
        System.out.println(sumElements);
    }
    public static void task7(int[][] arr)
    {
        int[] max = new int[arr.length];

        for (int i = 0; max.length > i; i++)
        {
            max[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; arr.length > i; i++)
        {
            for (int j = 0; arr[i].length > j; j++)
            {
                if (arr[i][j] > max[i])
                {
                    max[i] = arr[i][j];
                }
            }
            System.out.println(max[i]);
        }
    }
}
