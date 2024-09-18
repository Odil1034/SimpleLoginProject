package uz.pdp.SimpleLoginProject.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateBookDto {
    String title;
    String author;
    String description;
    Integer countOfPages;
}
