package com.sonu.authorizationsystem.controller;

import com.sonu.authorizationsystem.model.request.BookRequest;
import com.sonu.authorizationsystem.model.response.BookResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/*
Rest controller to demonstrate capabilities of fine-grained role-privileges. Currently, we are using
READ/WRITE/UPDATE/DELETE
 */

@RestController()
@RequestMapping("/book")
public class BookController {
    @PostMapping
    @PreAuthorize("hasAuthority('WRITE') ")
    public ResponseEntity<BookResponse> postBook(@RequestBody BookRequest bookRequest, @RequestHeader("Authorization") String authorizationHeader) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("New " + bookRequest.getTitle() + "Book Added ");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResponse);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ') ")
    public ResponseEntity<BookResponse> fetchBookById(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("Book with id:" + id + " fetched successfully");
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
        }
    }


    @GetMapping("/all")
    public ResponseEntity<BookResponse> fetchAllBooks(@RequestParam int page, @RequestParam int size) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("All books are retrieved successfully");
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE') ")
    public ResponseEntity<BookResponse> removeBookById(@PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("Book with id:" + id + " deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE') ")
    public ResponseEntity<BookResponse> modifyBookById(@RequestBody BookRequest bookRequest, @PathVariable Long id, @RequestHeader("Authorization") String authorizationHeader) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setMessage("Book with id:" + id + " updated successfully");
        if (id == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(bookResponse);
        }
    }

}
