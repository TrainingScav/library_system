package dto;

import lombok.*;

@Data // Getter, Setter, ToString 포함된 annotation
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String isbn;
    private boolean available;

}
