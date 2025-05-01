package com.homework;

public enum Group {
    Job("Работа"), Family("Семья"), Friends("Друзья");
    private String title;

    Group(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Group{" +
                "title='" + title + '\'' +
                '}';
    }
}