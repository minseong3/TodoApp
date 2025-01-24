<template>
    <TodoHeader></TodoHeader>
    <TodoClock></TodoClock>
    <TodoInput @add = "addTodo" @clear = "clearTodos"></TodoInput>
    <TodoList :todoItems = "todoItems" @remove ="removeTodo" @complete = "completeTodo"></TodoList>
    <TodoFooter></TodoFooter>
</template>

<script setup>
import TodoHeader from '@/components/TodoHeader.vue';
import TodoInput from './components/TodoInput.vue';
import TodoList from './components/TodoList.vue';
import TodoFooter from './components/TodoFooter.vue';
import TodoClock from './components/TodoClock.vue';
import { useTodo } from '@/composable/useTodo';

const { todoItems, addTodo, completeTodo, removeTodo, clearTodos } = useTodo();
</script>

<style scoped>
</style>