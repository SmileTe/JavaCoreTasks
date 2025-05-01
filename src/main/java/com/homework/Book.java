package com.homework;

import java.util.*;

public class Book {
    private List<Contact> contactList;
    private Set<Contact> contactSet;
    private Map<Group, Set<Contact>> contactMap;

    public Book() {
        this.contactList = new ArrayList<>();
        this.contactSet = new HashSet<>();
        this.contactMap = new HashMap<>();
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            String menu = "Введите число 1–5:\n" + "1. Добавить контакт\n" + "2. Удалить контакт\n" + "3. Посмотреть все контакты\n" + "4. Найти контакт\n" + "5. Посмотреть контакты по группе";

            int number = 0;

            while (true) {
                System.out.println(menu);
                try {
                    number = scanner.nextInt();
                    scanner.nextLine();
                } catch (NumberFormatException | InputMismatchException e) {
                    scanner.nextLine();
                    System.out.println("Надо ввести число");
                    number = 0;
                }
                switch (number) {
                    case 1:
                        // Добавить контакт
                        try {
                            addNewContact(scanner);
                        } catch (IllegalArgumentException | InputMismatchException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        //Удалить контакт
                        try {
                            deleteContact(scanner);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        // Посмотреть все контакты
                        for (Contact contact : contactSet) {
                            System.out.println(contact);
                        }
                        break;
                    case 4:
                        //Найти контакт
                        System.out.print("Введите имя: ");
                        String name = scanner.nextLine();
                        name = name.trim();

                        findContact(name.trim());

                        break;
                    case 5:
                        //Посмотреть контакты по группе
                        System.out.println("Введите число 1-3 для выбора группы");
                        System.out.println("1-Работа 2-Семья 3-Друзья");
                        int numberGroup = scanner.nextInt();
                        scanner.nextLine();
                        Group group = null;
                        if (numberGroup == 1) {
                            group = Group.Job;
                        } else if (numberGroup == 2) {
                            group = Group.Family;
                        } else if (numberGroup == 3) {
                            group = Group.Friends;
                        }
                        boolean findContacts = false;
                        if (contactMap.containsKey(group)) {
                            Set<Contact> contacts = contactMap.get(group);
                            for (Contact contact :
                                    contacts) {
                                System.out.println(contact);
                                findContacts = true;
                            }
                        }
                        if (!findContacts) {
                            System.out.printf("Контакты в группе %s не найдены!", group.toString());
                            System.out.println();
                        }
                        break;
                    default:
                        return;
                }

            }
        }
    }

    public void addNewContact(Scanner scanner) {

        System.out.print("Введите имя: ");
        String name = scanner.nextLine();

        System.out.print("Введите номер телефона: ");
        int phone = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите e-mail: ");
        String email = scanner.nextLine();

        System.out.println("Введите число 1-3 для выбора группы");
        System.out.println("1-Работа 2-Семья 3-Друзья");
        int numberGroup = scanner.nextInt();
        scanner.nextLine();
        Group group = null;
        if (numberGroup == 1) {
            group = Group.Job;
        } else if (numberGroup == 2) {
            group = Group.Family;
        } else if (numberGroup == 3) {
            group = Group.Friends;
        }
        Contact contact = null;
        try {
            contact = new Contact(name, phone, email, group);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (!contactSet.contains(contact)) {
            contactList.add(contact);
            contactSet.add(contact);
            if (!contactMap.containsKey(group)) {
                Set<Contact> setFromMap = new HashSet<>();
                setFromMap.add(contact);
                contactMap.put(group, setFromMap);
            } else {
                Set<Contact> setFromMap = contactMap.get(group);
                setFromMap.add(contact);
                contactMap.put(group, setFromMap);
            }
        }
    }

    public void deleteContact(Scanner scanner) {
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Не введено имя");
        }
        boolean findingContact = false;
        for (Contact contact : contactList) {
            if (contact.getName().equals(name)) {
                Contact findContact = contact;
                contactList.remove(findContact);
                contactSet.remove(findContact);
                Set<Contact> contactsFromMap = contactMap.get(contact.getGroup());
                contactsFromMap.remove(contact);
                contactMap.put(contact.getGroup(), contactsFromMap);
                System.out.println("Контакт " + name + " удален");
                findingContact = true;
                break;
            }
        }
        if (!findingContact) {
            System.out.println("Нет контакта с таким именем");
        }
    }

    public void findContact(String name) {
        if (name.isEmpty()) {
            System.out.println("Не введено имя");
            return;
        }
        boolean findContact = false;
        Iterator<Contact> i = contactSet.iterator();
        while (i.hasNext()) {
            Contact contact = i.next();
            if (contact.getName().contains(name)) {
                System.out.println(contact);
                findContact = true;
            }
        }
        if (!findContact) {
            System.out.println("Контакт с таким именем не найден");
        }
    }
}