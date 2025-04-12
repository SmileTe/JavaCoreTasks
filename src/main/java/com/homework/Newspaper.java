package com.homework;

public class Newspaper extends Publication implements Printable {
    private String publicationDay;

    public Newspaper(String title, String author, int year, String publicationDay) {
        super(title, author, year);
        setPublicationDay(publicationDay);
    }

    @Override
    public String getType() {
        return "Газета";
    }

    @Override
    public String toString() {
        return super.toString() + " publication day: " + getPublicationDay();
    }

    @Override
    public boolean equals(Object obj) {
        Newspaper newspaper = (Newspaper) obj;
        return super.equals(obj)
                && (publicationDay != null ? newspaper.equals(newspaper.publicationDay) : newspaper.publicationDay == null);
    }

    @Override
    public int hashCode() {
        return super.hashCode() + (publicationDay != null ? publicationDay.hashCode() : 0);
    }

    public String getPublicationDay() {
        return publicationDay;
    }

    public void setPublicationDay(String publicationDay) {
        this.publicationDay = publicationDay;
    }

    @Override
    public void printDetails() {
        System.out.println(this.toString());
    }

}
