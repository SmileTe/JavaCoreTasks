package com.homework;

import java.util.Scanner;


public class ContactBook {
    static User[] users = new User[100];

    public void start() {
        short amountUsers = 0;
        //вывод сообщения с пунктами меню
        String menu = "Введите выбранное число:\n" +
                "1 - Добавление нового контакта\n" +
                "2 - Просмотр всех контактов\n" +
                "3 - Поиск контакта по имени\n" +
                "4 - Удаление контакта\n" +
                "5 - Выход из программы";

        int number = 0;
        Scanner scanner = new Scanner(System.in);
        while (number != 5) {
            System.out.println(menu);
            try {
                number = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Надо ввести число от 1 до 5");
                number = 0;
                return;

            }

            switch (number) {
                case 1: //Добавление нового контакта
                    if (addNewContact(amountUsers)) {
                        amountUsers++;
                    }
                    break;
                case 2: //Просмотр всех контактов:
                    printAllContacts();
                    break;
                case 3: //Поиск контакта по имени:
                    findContactByName();
                    break;
                case 4: //Удаление контакта:
                    if (deleteContactByName()) {
                        amountUsers--;
                    }
                    break;
                case 5: //Выход из программы:
                    break;
                default:
                    System.out.println("Не введено число");
                    break;
            }
        }
    }

    public boolean addNewContact(short amountUser) {

        if (amountUser == users.length) {
            System.out.println("Нет свободного места в массиве");
            return false;
        }
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя контакта");

        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Введена пустая строка");
            return false;
        }

        for (int i = 0; i < users.length; i++) {
            if (users[i] != null
                    && users[i].getName().equals(name)) {
                System.out.println("Контакт с таким именем уже существует");
                return false;
            }
        }

        int pnoneNumber;
        System.out.println("Введите номер телефона");
        try {
            pnoneNumber = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Надо ввести число");
            return false;
        }

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

    public void findContactByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя контакта");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
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
                break;
            }
        }
        if (!userExists) {
            System.out.println("Нет такого контакта");
        }
    }

    public void printAllContacts() {
        for (User user : users) {
            if (user == null) {
                break;
            }
            System.out.println(user);
        }
    }

    public boolean deleteContactByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя контакта");
        String name = scanner.nextLine();

        if (name.isEmpty()) {
            System.out.println("Введена пустая строка");
            return false;
        }

        int position = 0;
        boolean isSuchUser = false;

        //ввод имени
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null) {
                if (users[i].getName().equals(name)) {
                    isSuchUser = true;
                    position = i;
                    break;
                }
            }
        }

        if (!isSuchUser) {
            System.out.println("Не найден контакт с таким именем");
            return false;
        }

        for (int i = position; i < users.length; i++) {
            if (i == users.length - 1) {
                users[i] = null;
            } else {
                users[i] = users[i + 1];
            }
        }

        System.out.println("Контакт удален");
        return true;
    }
}
