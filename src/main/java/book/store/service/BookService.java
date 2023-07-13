package book.store.service;

import book.store.dto.BookDTO;
import book.store.mapper.BookMapper;
import book.store.model.BookModel;
import book.store.model.GenreModel;
import book.store.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
     private final BookRepository bookRepository;
     private final GenreService genreService;
    private final BookMapper bookMapper;

     public List<BookDTO> getBooks(){
         return bookMapper.toDtoList(bookRepository.findAll());
     }
    public List<BookModel> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDTO getbook(Long id){

         return  bookMapper.toDto(bookRepository.findById(id).orElse(new BookModel()));
    }
     public List<BookModel>searchBook(String key){
         List<BookModel>bookModelList=bookRepository.searchBooks("%"+key+"%");
         return bookModelList;
     }


    public BookModel addBook(BookModel book) {

         return bookRepository.save(book);
    }
     public BookModel getBook(Long id){
         return bookRepository.findById(id).orElse(null);
     }
     public BookModel saveBook(BookModel bookModel){

         return bookRepository.save(bookModel);

     }
     public  void assignGenre(Long bookId,Long genreId){
         BookModel bookModel=getBook(bookId);
         GenreModel genreModel=genreService.getGenre(genreId);

         if (bookModel.getGenres() !=null && bookModel.getGenres().size()>0) {
             if (!bookModel.getGenres().contains(genreModel)){
                 bookModel.getGenres().add(genreModel);
             }
         }else{
             List<GenreModel> genreModelList=new ArrayList<>();
             genreModelList.add(genreModel);
             bookModel.setGenres(genreModelList);
         }
         bookRepository.save(bookModel);

     }
     public  void unassignGenre(Long bookId,Long genreId){

         BookModel bookModel=bookRepository.findById(bookId).orElseThrow();
         GenreModel genreModel=genreService.getGenre(genreId);

         if (bookModel.getGenres() !=null && bookModel.getGenres().size()>0){
             bookModel.getGenres().remove(genreModel);
         }
         bookRepository.save(bookModel);
     }
     public BookDTO updateBook(BookDTO book){
         return bookMapper.toDto(bookRepository.save(bookMapper.toModel(book)));
     }
     public void delete(Long id){

         bookRepository.deleteById(id);
     }
}
