package book.store.api;

import book.store.dto.BookDTO;
import book.store.mapper.BookMapper;
import book.store.model.BookModel;
import book.store.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/book")
@RequiredArgsConstructor
public class BookRestController {
    private final BookService bookService;

    private final BookMapper bookMapper;


     @GetMapping
    public List <BookDTO> bookModelList(){
            return bookService.getBooks();
     }

    @GetMapping(value = "{id}")
    public BookDTO getBook(@PathVariable(name = "id")Long id){
         return bookService.getbook(id);
    }
    @PostMapping
    public  BookDTO addBook(@RequestBody BookModel bookModel){
         return bookMapper.toDto(bookService.addBook(bookModel));
    }
    @PutMapping
    public BookModel updateBook(@RequestBody BookModel bookModel){
         return bookService.saveBook(bookModel);

    }
    @DeleteMapping(value = "{id}")
    public void deleteBook(@PathVariable(name = "id")Long id){

         bookService.delete(id);
    }


}
