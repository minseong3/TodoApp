import { ref, onMounted } from 'vue';
import {
    fetchTodos,
    addTodoItem,
    completeTodoItem,
    removeTodoItem,
    clearAllTodoItems,
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
            const todo = await addTodoItem(newTodo);
            todoItems.value.push(todo);
        } catch (error) {
            console.error('Failed to add todo: ', error);
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
                const currentCompleted = todoItems.value[todoIndex].completed;
                const updatedCompleted = await completeTodoItem(id, {completed: !currentCompleted});

                todoItems.value[todoIndex] = updatedCompleted;
            }
        } catch (error) {
            console.error('Failed to complete todo: ', error);
        }
    };

    const removeTodo = async (id, index) => {
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

    onMounted(() => {
        loadTodos();
    });

    return { todoItems, addTodo, completeTodo, removeTodo, clearTodos };
}