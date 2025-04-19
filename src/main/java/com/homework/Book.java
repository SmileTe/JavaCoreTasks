package com.homework;

import java.util.Objects;

public class Book extends Publication implements Printable {
    private String isbn;

    public Book(String title, String author, int year, String isbn) {
        super(title, author, year);
        setIsbn(isbn);
    }

    @Override
    public String getType() {
        return "Книга";
    }

    @Override
    public String toString() {
        return super.toString() + " ISBN: " + getIsbn();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isbn);
    }


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String ISBN) {
        this.isbn = ISBN;
    }

    @Override
    public void printDetails() {
        System.out.println(this.toString());
    }
}
