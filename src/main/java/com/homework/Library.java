package com.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<Publication> publications = new ArrayList<>();

    public void start() {
        System.out.println();
        //вывод сообщения с пунктами меню
        String menu = "Введите выбранное число:\n" +
                "1 - Добавить новую публикацию\n" +
                "2 - Вывести список всех публикаций\n" +
                "3 - Поиск публикации по автору.\n" +
                "4 - Вывести общее количество публикаций (используя статический метод).\n" +
                "0 - Выход";

        int number = 0;
        Scanner scanner = new Scanner(System.in);
        while (number != 5) {
            System.out.println(menu);
            try {
                number = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Надо ввести число от 0 до 4");
                number = 0;
                return;

            }
            switch (number) {
                case 1:  //Добавить новую публикацию
                    addNewPublication();
                    break;
                case 2: //Вывести список всех публикаций.
                    listPublications();
                    break;
                case 3: //Поиск публикации по автору
                    System.out.println("Введите автора");
                    String author = scanner.nextLine();
                    author.trim();
                    if (!author.isEmpty()) {
                        searchByAuthor(author);
                    }
                    break;
                case 4: // Вывести общее количество публикаций (используя статический метод).
                    System.out.println(Publication.getPublicationCount());
                    break;
                case 0: //Выход:
                    return;
                default:
                    System.out.println("Не введено число");
                    break;
            }

        }
        scanner.close();
    }

    public void addNewPublication() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип публикации:\n"
                + "1-Книга\n"
                + "2-Журнал\n"
                + "3-Газета");

        int type = 0;
        try {
            type = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Нужно ввести число от 1 до 3");
            return;
        }

        if (type == 1) {
            //Книга
            System.out.println("Введите название, автора, год, ISBN через ';'");
            String line = scanner.nextLine();
            String[] words = line.split(";");
            if (words.length != 4) {
                System.out.println("Введены некорректные данные");
                return;
            }
            int year;
            try {
                year = Integer.valueOf(words[2]);
            } catch (NumberFormatException e) {
                System.out.println("Введен некорректный год");
                return;
            }

            Book book = new Book(words[0], words[1], year, words[3]);
            addPublication(book);
        } else if (type == 2) {
            //Журнал
            System.out.println("Введите название, автора, год, номер выпуска через ';'");
            String line = scanner.nextLine();
            String[] words = line.split(";");
            if (words.length != 4) {
                System.out.println("Введены некорректные данные");
                return;
            }
            int year;
            try {
                year = Integer.valueOf(words[2]);
            } catch (NumberFormatException e) {
                System.out.println("Введен некорректный год");
                return;
            }
            int issueNumber;
            try {
                issueNumber = Integer.valueOf(words[3]);
            } catch (NumberFormatException e) {
                System.out.println("Введен некорректый номер выпуска");
                return;
            }

            Magazine magazine = new Magazine(words[0], words[1], year, issueNumber);
            addPublication(magazine);
        } else if (type == 3) {
            //Газета
            System.out.println("Введите название, автора, год, день публикации через ';'");
            String line = scanner.nextLine();
            String[] words = line.split(";");
            if (words.length != 4) {
                System.out.println("Введены некорректные данные");
                return;
            }
            int year;
            try {
                year = Integer.valueOf(words[2]);
            } catch (NumberFormatException e) {
                System.out.println("Введены некорректый год");
                return;
            }
            Newspaper newspaper = new Newspaper(words[0], words[1], year, words[3]);
            addPublication(newspaper);
        }


    }

    public void addPublication(Publication pub) {
        publications.add(pub);
        Publication.setPublicationCount(1);
    }

    public void listPublications() {
        for (Publication publication :
                publications) {
            System.out.println(publication.toString());
        }
    }

    public void searchByAuthor(String author) {
        for (Publication publication :
                publications) {
            if (publication.getAuthor().equals(author)) {
                System.out.println(publication.toString());
            }
        }
    }
}

