package book.store.repository;

import book.store.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookModel,Long>{
   @Query(
           value ="" +
                   "SELECT b" +
                   " FROM BookModel b" +
                   " WHERE b.id > 0 AND LOWER(b.name ) LIKE LOWER(:searchName)")
    List<BookModel> searchBooks(@Param("searchName") String bookName);
}
