package todo.todominsung.module.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RandomQuoteDto {
    private  Long id;
    private String author;
    private String authorProfile;
    private String message;
}
