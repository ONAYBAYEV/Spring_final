package book.store.repository;

import book.store.model.GenreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreModelRepository extends JpaRepository<GenreModel,Long> {

}
