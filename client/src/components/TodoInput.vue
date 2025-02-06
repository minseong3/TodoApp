<template>
  <div>
    <div class="add-input">
      <input v-model="todoInput" type="text" placeholder="할 일을 입력하세요" @keyup.enter="addTodo">
      <select v-model="selectedCategory">
        <option value="공부">공부</option>
        <option value="운동">운동</option>
        <option value="업무">업무</option>
        <option value="기타">기타</option>
      </select>
      <button class="b1" @click="addTodo">추가</button>
      <button class="b2" @click="clearAllTodoItems">전부 삭제</button>

      <div class="search">
        <select v-model="searchTodoCategory" @change="filterTodos">
          <option value="전체">전체</option>
          <option value="공부">공부</option>
          <option value="운동">운동</option>
          <option value="업무">업무</option>
          <option value="기타">기타</option>
        </select>
        <input v-model="keyword" type="text" placeholder="검색 키워드를 입력하세요" @keyup.enter="searchTodos">
        <button class="b3" @click="searchTodos">검색</button>
      </div>
    </div>
  </div>
</template>

<script setup>
  import { ref, defineEmits } from 'vue';

  // 부모 컴포넌트로 이벤트를 전달하기 위한 함수
  const emit = defineEmits(['add', 'clear', 'search']);

  // 입력된 할 일을 저장하는 반응형 변수 선언
  const todoInput = ref('');
  const keyword = ref('');
  const selectedCategory = ref("공부");
  const searchTodoCategory = ref("전체");

  // 현재 날짜를 추가하기위한 함수 선언
  const getCurrentDate = () => {
    const now = new Date();
    return `${now.getFullYear()}-${(now.getMonth() + 1).toString().padStart(2, '0')}-${now.getDate().toString().padStart(2, '0')}`;
  };

  // methodss
  async function addTodo() {
    const todo = todoInput.value;
    const categoryTodo = selectedCategory.value;
    // 입력값이 없으면 데이터를 추가하지 않음
    if (!todo.trim()) {
      return;
    }
    try {
      const newTodo = {
        text: todo,
        completed: false,
        category: categoryTodo,
        date: getCurrentDate()
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

  async function searchTodos() {
    const searchText = keyword.value;
    try {
      emit('search', searchText.trim());
    } catch (error) {
      console.error('Failed to search todos: ', error);
    }

  } async function filterTodos() {
    const searchCategory = searchTodoCategory.value;
    try {
      emit('filter', searchCategory);
    } catch (error) {
      console.error('Failed to filtering todos: ', error);
    }
  }

  async function clearAllTodoItems() {
    try {
      emit('clear');
    } catch (error) {
      console.error('Failed to clear todos: ', error);
    }
  }

  // 입력창 비우기
  const clearTodo = () => todoInput.value = '';
</script>

<style scoped>
.add-input {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  padding: 10px;
  font-size: 16px;
  margin: 20px auto;
  width: 100%;
}

.search {
  display: flex;
  gap: 10px;
}

.search select {
  padding-left: 80px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search input {
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.search button {
  padding: 8px 12px;
  font-size: 14px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.add-input input {
  padding: 8px;
  font-size: 14px;
  width: 200px; /* 입력 필드 크기 조절 */
  border: 1px solid #ccc;
  border-radius: 5px;
}

.add-input select {
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.add-input button {
  padding: 8px 12px;
  font-size: 14px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
}

.b1 {
  background-color: #007bff;
  color: white;
}

.b2 {
  background-color: #dc3545;
  color: white;
}

.b3 {
  background-color: green;
  color: white;
}
</style>