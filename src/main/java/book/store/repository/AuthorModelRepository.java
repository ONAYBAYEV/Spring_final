package book.store.repository;

import book.store.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorModelRepository extends JpaRepository<AuthorModel,Long> {

}
