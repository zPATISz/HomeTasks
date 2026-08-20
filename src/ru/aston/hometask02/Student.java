package ru.aston.hometask02;

import java.util.List;
import java.util.Objects;

public class Student {
    private final String name;
    private final int age;
    private final List<Book> books;

    public Student(String name, int age, List<Book> books){
        this.name = name;
        this.age = age;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : books){
            sb.append(book).append("\n\t");
        }
        return String.format("%s %d year old\n\t%s", name, age, sb).trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        return Objects.equals(name, ((Student) obj).name)
                && age == ((Student) obj).age
                && Objects.equals(books, ((Student) obj).books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, books);
    }
}
