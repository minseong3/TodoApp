package todo.todominsung.module.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import todo.todominsung.module.dto.TodoDto;
import todo.todominsung.module.mapper.TodoMapper;



@Service
public class TodoService {
    /* 의존성 주입 1.(생성자 방법) @RequiredArgConstruc.. => 순환참조 에러를 컴파일 시 발견 가능하기때문
     * 2. @Autowired(필드 주입)
     * 3. Setter 방식
     * 애너테이션 방식 사용해보기*/
    @Autowired
    private TodoMapper todoMapper;

    // todoDto를 db에 저장하고 반환
    public TodoDto createTodo(TodoDto todoDto) {
        todoDto.setDate(LocalDate.now()); // 현재 날짜 자동 설정
        todoMapper.insertTodo(todoDto);
        return todoDto;
    }
    // Todo 검색 조회
    public List<TodoDto> searchTodos(String keyword) {
        return todoMapper.searchedTodos(keyword);
    }

    // Todo 전체 조회
    public List<TodoDto> getAllTodos() {
        return todoMapper.findAll();
    }

    // Todo 필터링 조회
    public List<TodoDto> filteringTodos(String category) {
        return todoMapper.filteredTodos(category);
    }

    // db에서 id를 찾아 text를 변경하고 todoDto로 매핑 후 반환
    public void updateTodoText(Long id, String newText) {
        todoMapper.updateTodoText(id, newText);
    }

    // completed 값 반전 뒤 todoDto로 매핑 후 반환
    public TodoDto completeTodo(Long id, Boolean completed) {
        todoMapper.completeTodo(id, completed);
        return todoMapper.findById(id);
    }

    // id를 인자로 받아 db에서 todo 삭제
    public void deleteTodo(Long id) {
        todoMapper.deleteTodo(id);
    }

    // db에 저장되어있는 todo 전부 삭제
    public void deleteAll() {
        todoMapper.deleteAllTodos();
    }
}