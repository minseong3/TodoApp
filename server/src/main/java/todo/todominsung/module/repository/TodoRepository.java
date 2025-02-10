package todo.todominsung.module.repository;

import java.util.List;

import org.apache.ibatis.annotations.*;
import todo.todominsung.module.dto.TodoDto;

@Mapper
public interface TodoRepository {
    // SelectAllTodos
    List<TodoDto> findAll();

    // SelectSearchTodos
    List<TodoDto> searchedTodos(@Param("keyword") String keyword);

    // SelectFilteringTodos
    List<TodoDto> filteredTodos(@Param("category") String category);

    // Insert 쿼리
    void insertTodo(TodoDto todoDto);

    // Update 쿼리 .. @Param 사용 이유 : mybatis의 parameterType은 단일 객체 또는 단일 값만 가능하므로
    void updateTodo(@Param("id") Long id, @Param("text") String text);

    void updateTodoTime(@Param("id") Long id);

    // Update Completed 쿼리
    void completeTodo(@Param("id") Long id, @Param("completed") Boolean completed);

    // Delete 쿼리
    void deleteTodo(Long id);

    // DeleteAll 쿼리
    void deleteAllTodos();
}