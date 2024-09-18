package uz.pdp.SimpleLoginProject.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import uz.pdp.SimpleLoginProject.repository.BookRepository;
import uz.pdp.SimpleLoginProject.dtos.CreateBookDto;
import uz.pdp.SimpleLoginProject.exceptions.BookNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SimpleLoginProject.model.Book;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/create")
    public Book bookCreate(@RequestBody CreateBookDto createBookDto) {

        Book build = Book.builder()
                .title(createBookDto.getTitle())
                .countOfPages(createBookDto.getCountOfPages())
                .author(createBookDto.getAuthor())
                .description(createBookDto.getDescription())
                .build();
        Book save = bookRepository.save(build);
        return save;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Book>> getById(@PathVariable Integer id) {
        Optional<Book> book = Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found", HttpStatus.NOT_FOUND)));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/get-page/{page}/{countOfItems}")
    public ResponseEntity<List<Book>> getBooksForPage(
            @PathVariable Integer page, @PathVariable Integer countOfItems) {
        Pageable pageRequest = PageRequest.of(page, countOfItems);
        List<Book> booksForPage = bookRepository.getBooksForPage(pageRequest);
        return ResponseEntity.ok(booksForPage);
    }

    @GetMapping("/get-page-info/{page}/{countOfItems}")
    public ResponseEntity<Page<Book>> getBooksWithPageInfo(
            @PathVariable Integer page, @PathVariable Integer countOfItems) {
        Pageable pageRequest = PageRequest.of(page, countOfItems);
        Page<Book> all = bookRepository.findAll(pageRequest);
        return ResponseEntity.ok(all);
    }

}
