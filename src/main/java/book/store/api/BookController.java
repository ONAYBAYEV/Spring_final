package book.store.api;

import book.store.dto.BookDTO;
import book.store.model.BookModel;
import book.store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/book")
public class BookController {
    private final BookService bookService;


    @GetMapping(value = "/get-book-list")
    public List<BookDTO> getBooks() {
        return bookService.getBooks();
    }
//    @PostMapping(value = "/add-kitap")
//    public BookDTO addKitap(@RequestBody BookDTO bookDTO){
//        return bookService.A(bookDTO);
//    }
//}
}