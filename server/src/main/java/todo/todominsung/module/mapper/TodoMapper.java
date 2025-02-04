package todo.todominsung.module.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import todo.todominsung.module.dto.TodoDto;
@Mapper
public interface TodoMapper {
    //!-- 쿼리문 --!//
    // SelectAllTodos
    List<TodoDto> findAll();

    // SelectSearchTodos
    List<TodoDto> searchedTodos(@Param("keyword") String keyword);

    // SelectFilteringTodos
    List<TodoDto> filteredTodos(@Param("category") String category);

    // Insert 쿼리
    void insertTodo(TodoDto todoDto);

    // Update 쿼리 .. @Param 사용 이유 : mybatis의 parameterType은 단일 객체 또는 단일 값만 가능하므로
    void updateTodoText(@Param("id") Long id, @Param("text") String text);

    // Update Completed 쿼리
    @Update("UPDATE todo SET completed = #{completed} WHERE id = #{id}")
    int completeTodo(@Param("id") Long id, @Param("completed") Boolean completed);

    @Select("SELECT * FROM todo WHERE id = #{id}")
    @Results(id = "todoDtoMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "text", property = "text"),
            @Result(column = "completed", property = "completed"),
            @Result(column = "date", property = "date")
    })
    TodoDto findById(@Param("id") Long id);

    // Delete 쿼리
    void deleteTodo(Long id);

    // DeleteAll 쿼리
    void deleteAllTodos();
}