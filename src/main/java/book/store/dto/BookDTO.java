package book.store.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {

    private  Long id;
    private String title;
    private  String description;
    private  String img;
    private int price;



}
