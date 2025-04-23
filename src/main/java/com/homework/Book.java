package com.homework;

import java.util.Objects;

public class Book {
    String title;
    String author;
    int availableCopies;

    public Book(String title, String author, int availableCopies) throws ZeroAvailableCopies {
        if (availableCopies == 0) {
            throw new ZeroAvailableCopies("Количество доступных копий не может быть 0!");
        }
        if (title.isEmpty()) {
            throw new IllegalArgumentException("Не введено название");
        }
        if (author.isEmpty()) {
            throw new IllegalArgumentException("Не введено имя автора");
        }
        this.title = title;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", availableCopies=" + availableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return availableCopies == book.availableCopies && Objects.equals(title, book.title) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, availableCopies);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
}
