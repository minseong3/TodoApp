<template>
  <div class="container">
    <input v-model="todoInput" type="text" placeholder="할 일을 입력하세요" @keyup="addTodo">
    <button class="b1" @click="addTodo">추가</button>
    <button class="b2" @click="clearAllTodoItems">전부 삭제</button>
  </div>
</template>

<script setup>
  import { ref, defineEmits } from 'vue';

  // 부모 컴포넌트로 이벤트를 전달하기 위한 함수
  const emit = defineEmits(['add', 'clear']);

  // 입력된 할 일을 저장하는 반응형 변수 선언
  const todoInput = ref('');

  // methodss
  async function addTodo() {
    const todo = todoInput.value;
    // 입력값이 없으면 데이터를 추가하지 않음
    if (!todo.trim()) {
      return;
    }
    try {
      const newTodo = {
        text: todo,
        completed: false
      };
      emit('add', newTodo); // 서버 응답 데이터를 부모 컴포넌트에 전송
      clearTodo();
    } catch (error) {
      console.error('Failed to add todo: ', error);
      if (error.response) {
        // 서버가 응답했지만 오류가 있을 경우
        console.error('Response error:', error.response.data);
      } else {
        // 요청이 아예 실패했을 경우
        console.error('Request error:', error.message);
      }
    }
  }

  async function clearAllTodoItems() {
    try {
      emit('clear');
    } catch (error) {
      console.error('Failed to clear todos: ', error);
    }
  }

  // 데이터를 추가하고나서 입력창 비우기
  const clearTodo = () => todoInput.value = '';
</script>

<style scoped>
/* 부모 컨테이너를 flexbox로 설정 */
  .container {
    flex-direction: column; /* 세로 방향으로 정렬 */
    justify-content: center; /* 수직 중앙 정렬 */
    align-items: center; /* 수평 중앙 정렬 */
    height: 5vh; /* 화면 전체 높이 사용 */
    text-align: center; /* 텍스트 중앙 정렬 */
    transform: translateX(100px);
  }

  input {
    padding: 10px;
    font-size: 16px;
    margin-bottom: 10px; /* 버튼과 간격을 줌 */
    width: 250px; /* 적당한 너비 설정 */
  }

  .b1 {
    background: linear-gradient(to right, #9fd8ff, #ff92aa);
    padding: 10px 20px;
    border-radius: 50px;
    min-width: 85px;
    font-size: 16px;
    font-weight: bold;
    color: #000000;
    cursor: pointer;
    margin-left: 10px;
    box-shadow: 0px 2px 2px 2px #ffffff40 inset, 0px -2px 2px 2px #00000020 inset;
  }
  .b2 {
    background: linear-gradient(to right, #ff0000, #f700ff);
    padding: 10px 20px;
    border-radius: 50px;
    min-width: 85px;
    font-size: 16px;
    font-weight: bold;
    color: white;
    cursor: pointer;
    margin-left: 10px;
    box-shadow: 0px 2px 2px 2px #ffffff40 inset, 0px -2px 2px 2px #00000020 inset;
    transform: translateX(550px);
  }
</style>