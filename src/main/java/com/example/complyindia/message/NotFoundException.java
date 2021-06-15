package com.example.complyindia.message;

public class NotFoundException extends Exception {

public NotFoundException(long book_id) {
        super(String.format("Book is not found with id : '%s'", book_id));
        }
}
