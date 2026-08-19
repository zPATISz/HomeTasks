package ru.aston.hometask02;

public class Book {
    private String name;
    private int pages;
    private int year;

    public Book(String name, int pages, int year) {
        this.name = name;
        this.pages = pages;
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }
}
