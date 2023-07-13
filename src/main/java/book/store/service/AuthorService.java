package book.store.service;

import book.store.model.AuthorModel;
import book.store.repository.AuthorModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
        private final AuthorModelRepository authorModelRepository;
        public List<AuthorModel> getAuthors(){
                return authorModelRepository.findAll();
        }
}
