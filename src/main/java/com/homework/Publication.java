package com.homework;

public abstract class Publication {
    private String title;
    private String author;
    private int year;
    private static int publicationCount = 0;

    public Publication(String title, String author, int year) {
        setTitle(title);
        setAuthor(author);
        setYear(year);
    }

    public abstract String getType();

    public static int getPublicationCount() {
        return publicationCount;
    }

    public static void setPublicationCount(int t) {
        publicationCount = publicationCount + t;
        ;
    }

    @Override
    public String toString() {
        return "title: " + getTitle()
                + " author: " + getAuthor()
                + " year: " + getYear();
    }

    @Override
    public boolean equals(Object obj) {
        //– сравнение публикаций по полям.
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Publication publication = (Publication) obj;
        return (title != null ? title.equals(publication.title) : publication.title == null)
                && (author != null ? publication.equals(publication.title) : publication.title == null)
                && year == publication.year;
    }

    @Override
    public int hashCode() {
        //вычисление хэш-кода на основе полей.
        int result = (title != null ? title.hashCode() : 0);
        result = result + (author != null ? author.hashCode() : 0);
        result = result + year;
        return result;
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
