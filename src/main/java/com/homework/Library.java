package com.homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {
    private List<Publication> publications = new ArrayList<>();

    private static int publicationCount = 0;

    public void start() {
        System.out.println();
        //вывод сообщения с пунктами меню
        String menu = "Введите выбранное число:\n" +
                "1 - Добавить новую публикацию\n" +
                "2 - Вывести список всех публикаций\n" +
                "3 - Поиск публикации по автору.\n" +
                "4 - Вывести общее количество публикаций\n" +
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
                    author = author.trim();
                    if (!author.isEmpty()) {
                        searchByAuthor(author);
                    }
                    break;
                case 4: // Вывести общее количество публикаций
                    System.out.println(getPublicationCount());
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
        System.out.println("Выберите тип публикации:\n" + "1-Книга\n" + "2-Журнал\n" + "3-Газета");

        int type = 0;
        try {
            type = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Нужно ввести число от 1 до 3");
            return;
        }

        switch (type) {
            case 1:
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
                    year = Integer.valueOf(words[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Введен некорректный год");
                    return;
                }

                Book book = new Book(words[0].trim(), words[1].trim(), year, words[3].trim());
                addPublication(book);
                break;

            case 2:
                //Журнал
                System.out.println("Введите название, автора, год, номер выпуска через ';'");
                String lineMagazine = scanner.nextLine();
                String[] wordsMagazine = lineMagazine.split(";");
                if (wordsMagazine.length != 4) {
                    System.out.println("Введены некорректные данные");
                    return;
                }
                int yearMagazine;
                try {
                    yearMagazine = Integer.valueOf(wordsMagazine[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Введен некорректный год");
                    return;
                }
                int issueNumber;
                try {
                    issueNumber = Integer.valueOf(wordsMagazine[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Введен некорректый номер выпуска");
                    return;
                }

                Magazine magazine = new Magazine(wordsMagazine[0].trim(), wordsMagazine[1].trim(), yearMagazine, issueNumber);
                addPublication(magazine);
                break;

            case 3:
                //Газета
                System.out.println("Введите название, автора, год, день публикации через ';'");
                String lineNewspaper = scanner.nextLine();
                String[] wordsNewspaper = lineNewspaper.split(";");
                if (wordsNewspaper.length != 4) {
                    System.out.println("Введены некорректные данные");
                    return;
                }
                int yearNewspaper;
                try {
                    yearNewspaper = Integer.valueOf(wordsNewspaper[2].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Введены некорректый год");
                    return;
                }
                Newspaper newspaper = new Newspaper(wordsNewspaper[0].trim(), wordsNewspaper[1].trim(), yearNewspaper, wordsNewspaper[3].trim());
                addPublication(newspaper);
                break;
            default:
                System.out.println("Не введено число");
                break;
        }
    }

    public void addPublication(Publication pub) {
        publications.add(pub);
        setPublicationCount(1);
    }

    public void listPublications() {
        for (Publication publication :publications) {
            Printable printable = (Printable)publication;
            printable.printDetails();
        }
    }

    public void searchByAuthor(String author) {
       boolean authorFound = false;
        for (Publication publication :
                publications) {
            if (publication.getAuthor().equals(author)) {
                System.out.println(publication.toString());
                authorFound = true;

            }
        }
        if(!authorFound){
            System.out.println("Такой автор не найден!");
        }
    }

    public static int getPublicationCount() {
        return publicationCount;
    }

    public static void setPublicationCount(int t) {
        publicationCount = publicationCount + t;
        ;
    }
}

