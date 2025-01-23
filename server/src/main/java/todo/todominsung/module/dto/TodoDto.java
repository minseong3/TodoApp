package todo.todominsung.module.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoDto {
    private Long id;
    private String text;
    private Boolean completed = false;
}
