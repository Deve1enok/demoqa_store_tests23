package models;

import lombok.Data;

import java.util.List;

@Data
public class BookAddedResponseApi {
   private List<ListOfIsbn> books;

}
