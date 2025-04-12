package com.homework;

public class Magazine extends Publication implements Printable {
    private int issueNumber;

    public Magazine(String title, String author, int year, int issueNumber) {
        super(title, author, year);
        setIssueNumber(issueNumber);
    }

    @Override
    public String getType() {
        return "Журнал";
    }

    @Override
    public String toString() {
        return super.toString() + " issue number: " + getIssueNumber();
    }

    @Override
    public boolean equals(Object obj) {
        Magazine magazine = (Magazine) obj;
        return super.equals(obj)
                && issueNumber == magazine.issueNumber;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    @Override
    public void printDetails() {
        System.out.println(this.toString());
    }

}
