package book.store;

import book.store.dto.BookDTO;
import book.store.mapper.BookMapper;
import book.store.model.BookModel;
import book.store.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StoreApplicationTests {
	@Autowired
	private BookMapper bookMapper;
	@Test
	void contextLoads() {
	}

	@Test
	void checkBookDTO() {
		BookModel bookModel = new BookModel();
		bookModel.setId(362L);
		bookModel.setName("Koshpendiler");
		bookModel.setDescription("Taryh kitaby");
		bookModel.setPrice(5000);
		BookDTO bookDTO = bookMapper.toDto(bookModel);
		Assertions.assertEquals(bookModel.getName(), bookDTO.getTitle());
		Assertions.assertEquals(bookModel.getId(), bookDTO.getId());
		Assertions.assertEquals(bookModel.getDescription(), bookDTO.getDescription());
		Assertions.assertEquals(bookModel.getPrice(), bookDTO.getPrice());
	}
}
