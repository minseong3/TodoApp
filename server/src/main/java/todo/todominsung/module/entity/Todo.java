package todo.todominsung.module.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 값 자동 설정(데이터베이스의 자동 증가)
    private Long id;
    private String text;
    @Column(nullable = false) // JPA의 Column으로 completed 기본값 false로 설정
    private Boolean completed = false;
}
