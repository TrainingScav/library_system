package dto;

import lombok.*;

@Data // Getter, Setter, ToString 포함된 annotation
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int id;
    private String name;
    private String studentId;
}
