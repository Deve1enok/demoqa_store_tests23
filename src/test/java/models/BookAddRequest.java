package models;

import lombok.Data;

import java.util.List;
@Data

public class BookAddRequest {
    private String userId;
    private List<ListOfIsbn> collectionOfIsbns;
    private String isbn;
}
