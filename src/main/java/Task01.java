import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Task01 {

    public static final int NORMAL_ARRAY_SIZE = 6;
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        String[] data = getData();
        System.out.println(Arrays.toString(data));
        while (!isFIOValid(data[0])) {
            System.out.println("Исправьте фамилию:");
            data[0] = scn.nextLine();
        }
        while (!isFIOValid(data[1])) {
            System.out.println("Исправьте имя:");
            data[1] = scn.nextLine();
        }
        while (!isFIOValid(data[2])) {
            System.out.println("Исправьте отчество:");
            data[2] = scn.nextLine();
        }
        while (!isDateValid(data[3])) {
            data[3] = scn.nextLine();
        }
        while (!isPhoneNumberValid(data[4])) {
            data[4] = scn.nextLine();
        }
        while (!isGenderCorrect(data[5])) {
            data[5] = scn.nextLine();
        }
        writeToFile(data);


        System.out.println("Фамилия: " + data[0]);
        System.out.println("Имя: " + data[1]);
        System.out.println("Отчество: " + data[2]);
        System.out.println("Дата рождения: " + data[3]);
        System.out.println("Номер телефона: " + data[4]);
        System.out.println("Пол: " + data[5]);
    }


    public static String[] getData() {
        System.out.println("Введите ФИО, дату рождения(дд.мм.гггг), номер телефона и пол(m/f) в одну строку через пробел :");
        while (true) {
            try {
                String input = scn.nextLine();
                String[] data = input.split(" ");
                if (data.length == NORMAL_ARRAY_SIZE) {
                    return data;
                } else {
                    throw new RuntimeException();
                }
            } catch (RuntimeException e) {
                System.out.println("Неверное количество данных! Введите ФИО, дату рождения(дд.мм.гггг), номер телефона и пол(m/f) в одну строку через пробел.");
            }
        }
    }

    public static boolean isFIOValid(String input) {
        try {
            if (input.matches("^[a-zA-Z]*$")) {
                return true;
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("Ошибка ввода!");
            return false;
        }
    }

    public static boolean isDateValid(String date) {
        SimpleDateFormat myFormat = new SimpleDateFormat("dd.MM.yyyy");
        myFormat.setLenient(false);
        try {
            myFormat.parse(date);
            return true;
        } catch (Exception e) {
            System.out.println("Некорректная дата! Повторите ввод даты в формате дд.мм.гггг");
            return false;
        }
    }

    public static boolean isPhoneNumberValid(String number) {
        try {
            if (number.matches("8\\d{10}")) {
                return true;
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("Некорректный номер телефона! Введите верный номер телефона");
            return false;
        }
    }

    public static boolean isGenderCorrect(String gender) {
        try {
            if (gender.equals("m") || gender.equals("f")) {
                return true;
            } else {
                throw new RuntimeException();
            }
        } catch (RuntimeException e) {
            System.out.println("Некорректный ввод пол! Введите: m/f");
            return false;
        }
    }

    public static void writeToFile(String[] data) {
        try (FileWriter filewriter = new FileWriter(data[0] + ".txt", true)) {
            for (String s : data) {
                filewriter.write(s + " ");
                filewriter.flush();
            }
            filewriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The file has been written");
    }
}

