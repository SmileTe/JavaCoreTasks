package com.homework;

public class Book extends Publication implements Printable {
    private String ISBN;

    public Book(String title, String author, int year, String ISBN) {
        super(title, author, year);
        setISBN(ISBN);
    }

    @Override
    public String getType() {
        return "Книга";
    }

    @Override
    public String toString() {
        return super.toString() + " ISBN: " + getISBN();
    }

    @Override
    public boolean equals(Object obj) {
        Book book = (Book) obj;
        return super.equals(obj)
                && (ISBN != null ? book.equals(book.ISBN) : book.ISBN == null);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (ISBN != null ? ISBN.hashCode() : 0);
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public void printDetails() {
        System.out.println(this.toString());
    }
}
