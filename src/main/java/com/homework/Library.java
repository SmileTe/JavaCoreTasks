package com.homework;

import java.util.*;

public class Library {
    private final List<Book> catalog;

    public Library() {
        catalog = new ArrayList<>();
    }

    public void start() {

        try (Scanner scanner = new Scanner(System.in)) {
            String menu = "Введите число 1–5:\n" +
                    "1. Вывести каталог\n" +
                    "2. Добавить объект\n" +
                    "3. Выдать объект\n" +
                    "4. Вернуть объект\n" +
                    "5. Выйти из приложения";

            int number = 0;

            while (number != 5) {
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
                        //Вывести каталог
                        for (Book book : getAllBooks()) {
                            System.out.println(book);
                        }
                        break;
                    case 2:
                        //Добавить объект
                        try {
                            addNewBook(scanner);
                        } catch (IllegalArgumentException | ZeroAvailableCopies e) {
                            System.out.println(e.getMessage());

                        }
                        break;
                    case 3:
                        //Выдать объект
                        System.out.print("Введите название: ");
                        String title = scanner.nextLine();
                        try {
                            takeBook(title.trim());
                        } catch (NoAvailableCopiesException | NoSuchElementException | IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        //Вернуть объект
                        System.out.print("Введите название: ");
                        String titleBook = scanner.nextLine();
                        try {
                            returnBook(titleBook.trim());
                        } catch (IllegalArgumentException | NoSuchElementException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    default:
                        break;
                }
            }

        }
    }

    public void addNewBook(Scanner scanner) {

        System.out.print("Введите название: ");
        String title = scanner.nextLine();

        System.out.print("Введите имя автора: ");
        String author = scanner.nextLine();

        System.out.print("Введите количество экземпляров: ");
        int copies = scanner.nextInt();
        scanner.nextLine();
        addBook(title, author, copies);

    }


    public void addBook(String title, String author, int copies) {
        try {
            Book book = new Book(title, author, copies);
            catalog.add(book);
        } catch (ZeroAvailableCopies | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            ;
        }
    }

    public void takeBook(String title) throws NoAvailableCopiesException {

        if (title.isEmpty()) {
            throw new IllegalArgumentException("Не введено название");
        }

        boolean bookFound = false;

        for (Book book :
                getAllBooks()) {
            if (book.getTitle().equals(title)) {
                int amountCopies = book.getAvailableCopies();
                if (amountCopies == 0) {
                    throw new NoAvailableCopiesException("Нет копий для выдачи");
                }
                book.setAvailableCopies(amountCopies - 1);
                bookFound = true;
            }
        }
        if (!bookFound) {
            throw new NoSuchElementException("Не найдена книга по названию");
        }
    }

    public void returnBook(String title) {

        if (title.isEmpty()) {
            throw new IllegalArgumentException("Не введено название");
        }

        boolean bookFound = false;

        for (Book book :
                getAllBooks()) {
            if (book.getTitle().equals(title)) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                bookFound = true;
            }
        }
        if (!bookFound) {
            throw new NoSuchElementException("Не найдена книга по названию");
        }
    }

    public List<Book> getAllBooks() {
        return catalog;
    }
}
