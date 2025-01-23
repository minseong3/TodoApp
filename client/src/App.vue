<template>
    <TodoHeader></TodoHeader>
    <TodoClock></TodoClock>
    <TodoInput @add="addTodoItem" @clear = "clearAllTodoItems"></TodoInput>
    <TodoList :todoItems = "todoItems" @remove ="removeTodoItem" @complete = "completeTodoItem"></TodoList>
    <TodoFooter></TodoFooter>
</template>

<script setup>
import { ref } from 'vue';
import TodoHeader from '@/components/TodoHeader.vue';
import TodoInput from './components/TodoInput.vue';
import TodoList from './components/TodoList.vue';
import TodoFooter from './components/TodoFooter.vue';
import TodoClock from './components/TodoClock.vue';
import apiClient from '@/api/axios';

// todoItems 배열 선언
const todoItems = ref([]);

// 백엔드에서 fetchTodo 받아오기
async function fetchTodos() {
  try {
    const response = await apiClient.get('/todos');
    todoItems.value = response.data;
  } catch (error) {
    console.error('Failed to fetch todos: ', error);
  }
}

// 서버에서 추가 할 데이터를 받아 todoItems 배열에 삽일
async function addTodoItem(newTodo) {
  try {
    const response = await apiClient.post('/todos', newTodo);
    todoItems.value.push(response.data);
  } catch (error) {
    console.error('Failed to add todo: ', error);
  }
}
// 서버에서 completed 데이터를 받아
async function completeTodoItem(id) {
  try {
    const response = await apiClient.patch(`/todos/${id}/complete`);
    const updatedTodo = response.data;

    const todoIndex = todoItems.value.findIndex((item) => item.id === id);
    if (todoIndex !== -1) {
      todoItems.value[todoIndex] = updatedTodo;
    }
  } catch (error) {
    console.error('Failed to complete todo: ', error);
  }
}

// TODO..Update a todo item

// Remove a todo item
async function removeTodoItem(id, index) {
  try {
    await apiClient.delete(`/todos/${id}`);
    todoItems.value.splice(index, 1);
  } catch (error) {
    console.error('Failed to remove todo: ', error);
  }
}

// Clear all todo items
async function clearAllTodoItems() {
  try {
    await apiClient.delete('/todos');
    todoItems.value = [];
  } catch (error) {
    console.error('Failed to remove todos: ', error);
  }
}

fetchTodos();
</script>

<style scoped>
</style>