import { ref, onMounted } from 'vue';
import {
    fetchTodos,
    addTodoItem,
    completeTodoItem,
    removeTodoItem,
    clearAllTodoItems,
    searchTodoItems,
    filteredTodos,
    updateTodoText
} from "@/api/todoApi";

export function useTodo() {
    const todoItems = ref([]);

    const loadTodos = async () => {
        try {
            todoItems.value = await fetchTodos();
        } catch (error) {
            console.error('Failed to fetch todos: ', error);
        }
    };

    const addTodo = async (newTodo) => {
        try {
            await addTodoItem(newTodo);
            loadTodos();
        } catch (error) {
            console.error('Failed to add todo: ', error);
        }
    };

    const searchTodos = async (keyword) => {
        try {
            if(!keyword.trim()) {
                // 검색어가 없으면 전체 목록 다시 로드
                await loadTodos();
                return;
            }
            todoItems.value = await searchTodoItems(keyword);
        } catch (error) {
            console.error('Failed to search todos: ', error);
        }
    };

    // Todo update
    const updateTodo = async (id, newText) => {
        if(!newText.trim()) {
            return;
        }
        try {
            await updateTodoText(id, newText);
            await loadTodos();
        } catch (error) {
            console.log('Failed to update todo: ', error);
        }
    };

    const completeTodo = async (id) => {
        if (!id) {
            console.error("ID가 유효하지 않습니다.");
            return;
        }
        try {
            // 현재 todo의 completed 값을 찾아 반전
            const todoIndex = todoItems.value.findIndex((item) => item.id === id);
            if(todoIndex !== -1) {
                // 기존 completed 값 반전
                const updatedCompleted = !todoItems.value[todoIndex].completed;

                // 서버 요청
                await completeTodoItem(id, { completed: updatedCompleted });

                // 최신 데이터 다시 불러오기
                await loadTodos();
            }
        } catch (error) {
            console.error('Failed to complete todo: ', error);
        }
    };

    const removeTodo = async (id, index) => {
        console.log(id);
        try {
            await removeTodoItem(id);
            todoItems.value.splice(index, 1);
        } catch (error) {
            console.error('Failed to remove todo: ', error);
        }
    };

    const clearTodos = async () => {
        try {
            await clearAllTodoItems();
            todoItems.value = [];
        } catch (error) {
            console.error('Failed to clear todos: ', error);
        }
    };

    const filteringTodos = async (category) => {
        try {
            todoItems.value = await filteredTodos(category);
        } catch (error) {
            console.error('Failed to filtering todos: ', error);
        }
    }

    onMounted(() => {
        loadTodos();
    });

    return { todoItems, addTodo, completeTodo, removeTodo, clearTodos, searchTodos, filteringTodos, updateTodo };
}