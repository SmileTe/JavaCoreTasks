package com.homework;

import java.util.Objects;

public class Contact {
    private String name;
    private int phone;
    private String email;
    private Group group;

    public Contact(String name, int phone, String email, Group group) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Не введено название");
        }
        if (phone == 0) {
            throw new IllegalArgumentException("Не введен телефон");
        }
        if (group == null) {
            throw new IllegalArgumentException("Не выбрана группа");
        }
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", group=" + group +
                '}';
    }
}
