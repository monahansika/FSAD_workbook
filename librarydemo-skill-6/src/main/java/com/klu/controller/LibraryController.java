package com.klu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.klu.model.Book;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private List<Book> bookList = new ArrayList<>();

    // 1. Welcome
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Online Library System!";
    }

    // 2. Count
    @GetMapping("/count")
    public int countBooks() {
        return 100;
    }

    // 3. Price
    @GetMapping("/price")
    public double bookPrice() {
        return 499.99;
    }

    // 4. List of Books
    @GetMapping("/books")
    public List<String> getBooks() {
        return Arrays.asList("Spring Boot", "Java Programming", "Microservices");
    }

    // 5. Book by ID
    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Details of Book with ID: " + id;
    }

    // 6. Search by Title
    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Searching for book titled: " + title;
    }

    // 7. Author Path Variable
    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by Author: " + name;
    }

    // 8. Add Book (POST)
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookList.add(book);
        return "Book Added Successfully!";
    }

    // 9. View All Added Books
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return bookList;
    }
}