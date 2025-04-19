package com.homework;

import java.util.Objects;

public abstract class Publication {
    private String title;
    private String author;
    private int year;

    public Publication(String title, String author, int year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    public abstract String getType();


    @Override
    public String toString() {
        return "title: " + getTitle()
                + " author: " + getAuthor()
                + " year: " + getYear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return year == that.year && Objects.equals(title, that.title) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
