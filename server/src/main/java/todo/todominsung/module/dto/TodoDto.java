package todo.todominsung.module.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TodoDto {
    private Long id;
    private String text;
    private Boolean completed = false;
    private LocalDate date;
    private String category;
}
