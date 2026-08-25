package ru.aston.hometask02;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private final String name;
    private final int pages;
    private final int year;

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

    @Override
    public int compareTo(Book o) {
        return Integer.compare(this.pages, o.pages);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        return Objects.equals(name, ((Book) obj).name)
                && pages == ((Book) obj).pages
                && year == ((Book) obj).year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, year, pages);
    }

    @Override
    public String toString() {
        return String.format("\"%s\", %d - %dp.", name, year, pages);
    }
}
