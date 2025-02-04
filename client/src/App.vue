<template>
  <TodoHeader></TodoHeader>
  <TodoClock class="todo-clock"></TodoClock>
  <TodoInput @add = "addTodo" @clear = "clearTodos" @search = "searchTodos" @filter = "filteringTodos"></TodoInput>
  <div class="grid-container2">
    <TodoList class="todo-list" :todoItems = "todoItems" @remove ="removeTodo" @complete = "completeTodo"></TodoList>
    <TodoFooter class="todo-footer"></TodoFooter>
  </div>
</template>

<script setup>
import TodoHeader from './components/TodoHeader.vue';
import TodoInput from './components/TodoInput.vue';
import TodoList from './components/TodoList.vue';
import TodoFooter from './components/TodoFooter.vue';
import TodoClock from './components/TodoClock.vue';
import { useTodo } from './composable/useTodo';

const { todoItems, addTodo, completeTodo, removeTodo, clearTodos, searchTodos, filteringTodos } = useTodo();
</script>

<style scoped>
  .grid-container2 {
    display: grid;
    grid-template-rows: 1fr auto;
    height: 45vh;
  }
  .todo-list {
    overflow-y: auto;
  }
  .todo-footer {
    height: 100px;
  }
</style>