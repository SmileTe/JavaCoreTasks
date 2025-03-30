package com.homework;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        User[] users = new User[2];
        short  amountUsers = 0;

        //вывод сообщения с пунктами меню
        String menu = "Введите выбранное число: 1 - Добавление нового контакта" +
                " 2 - Просмотр всех контактов 3 - Поиск контакта по имени 4 - Удаление контакта 5 - Выход из программы";

        int number = 0;
        Scanner scanner = new Scanner(System.in);
        while (number != 5) {
            System.out.println(menu);
            number = scanner.nextInt();
            switch (number) {
                case 1: //Добавление нового контакта
                    if(addNewContact(users,amountUsers)){
                        amountUsers++;
                    }
                    break;
                case 2: //Просмотр всех контактов:
                    printAllContacts(users);
                    break;
                case 3: //Поиск контакта по имени:
                    findContactByName(users);
                    break;
                case 4: //Удаление контакта:
                   if( deleteContactByName(users,amountUsers)){
                       amountUsers--;
                   }
                    break;
                case 5: //Выход из программы:
                    break;
                default:
                    System.out.println("Не введено число");
            }

        }
    }

    public static boolean addNewContact(User[] users, short amountUser) {

        if (amountUser == users.length) {
            System.out.println("Нет свободного места в массиве");
            return false;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя контакта");

        String name = scanner.nextLine();
        if (name.isEmpty()){
            System.out.println("Введена пустая строка");
            return false;
        }

        System.out.println("Введите номер телефона");
        String pnoneNumber = scanner.nextLine();

        for (int i = 0; i < users.length; i++) {
            if (users[i] == null) {
                User newUser = new User(name, pnoneNumber);
                users[i] = newUser;
                System.out.println("Контакт добавлен");
                return true;
            }
        }
        return false;
    }

    public static void findContactByName(User[] users) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя контакта");
        String name = scanner.nextLine();
        if (name.isEmpty()){
            System.out.println("Введена пустая строка");
            return;
        }
        boolean userExists = false;

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getName().equals(name)) {
                    System.out.println(users[i]);
                    userExists = true;

                }
            } else {
                if (!userExists) {
                    System.out.println("Нет такого контакта");
                }
                break;
            }
        }
    }

    public static void printAllContacts(User[] users) {
        for (User var : users) {
            if (var == null) {
                break;
            }
            System.out.println(var);
        }
    }

    public static boolean deleteContactByName(User[] users, short amountUsers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя контакта");
        String name = scanner.nextLine();

        if (name.isEmpty()){
            System.out.println("Введена пустая строка");
            return false;
        }

        User[] findingUsers = new User[100];
        int position = 0;

        //ввод имени
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getName().equals(name)) {
                    findingUsers[position] = users[i];
                    position++;
                }
            } else {
                return false;
            }
        }

        if (position == 0) {
            System.out.println("Не найден контакт с таким именем");
            return false;
        } else if (position == 1) {
            deleteContact(users, findingUsers[0]);
            System.out.println("Контакт удален");
            return true;
        } else {
            //несколько элементов
            System.out.println("Найдено несколько пользователей.");
            for (int i = 0; i < position; i++) {
                System.out.println("п." + i + " " + findingUsers[i]);

            }
            System.out.println("Введите номер позиции контакта для удаления");
                int number = scanner.nextInt();

                deleteContact(users, findingUsers[number]);
                System.out.println("Контакт удален");
                return true;

        }

    }

    public static void deleteContact(User[] users, User user) {
        int positionStart = 0;
        int positionEnd = users.length - 1;

        for (int i = 0; i < users.length; i++) {
            if (users[i] == user) {
                users[i] = null;
                positionStart = i;

            } else if (users[i] == null) {
                positionEnd = i;

            }
        }

        for (int i = positionStart; i < positionEnd - 1; i++) {
            users[i] = users[i + 1];
        }


    }
}
