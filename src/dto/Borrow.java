package dto;

import lombok.*;

import java.time.LocalDate;

@Data // Getter, Setter, ToString 포함된 annotation
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    private int id;
    private int student_id;
    private int book_id;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
