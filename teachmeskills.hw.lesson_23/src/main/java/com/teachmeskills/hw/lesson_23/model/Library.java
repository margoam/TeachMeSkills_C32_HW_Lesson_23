package com.teachmeskills.hw.lesson_23.model;

import jakarta.xml.bind.annotation.*;

import java.util.List;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class Library {

    @XmlElementWrapper
    @XmlElement(name = "book")
    List<Book> books;

    public Library() {}

    public Library(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "books=" + books +
                '}';
    }
}
