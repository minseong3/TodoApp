<template>
    <ul>
        <p class="t1" v-for="(item, index) in props.todoItems" :key="item.id">
            <span :class="{completed: item.completed}">{{ item.text }}</span>
            <span class="category">{{ item.category }}</span>
            <span class="date">{{ item.date }}</span>
            <button class="complete-btn" @click="completeTodo(item.id)">완료</button>
            <button class="delete-btn" @click="removeTodo(item.id, index)">삭제</button>
        </p>
    </ul>
</template>

<script setup>
  import { defineProps, defineEmits } from 'vue';

  // 부모 컴포넌트로부터 todoItems를 props로 받는다
  const props = defineProps({
    todoItems: Array,
  });

  console.log(props.todoItems);
  // 부모 컴포넌트로 이벤트 전송을 위한 emit 정의
  const emit = defineEmits(['remove', 'complete']);
      async function removeTodo(id, index) {
        try{
          emit('remove', id, index);
        } catch (error) {
          console.error('Failed to remove todo: ', error);
        }
      }

      async function completeTodo(id) {
        try {
          emit('complete', id);
        } catch (error) {
          console.error('Failed to complete todo: ', error);
        }
      }
</script>

<style scoped>
  ul {
    font-size: 30px;
    margin-bottom: 5px;
    text-align: center;
    /* overflow-x: hidden; */
  }
  .complete-btn {
    padding: 8px 12px;
    font-size: 14px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    background-color: #ff9100;
    color: white;
    margin-left: 20px;
  }

  .delete-btn {
    padding: 8px 12px;
    font-size: 14px;
    border: none;
    cursor: pointer;
    border-radius: 5px;
    background-color: #a19ea9;
    color: white;
    margin-left: 20px;
  }
  @font-face {
      font-family: 'Ownglyph_StudyHard-Rg';
      src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2411-3@1.0/Ownglyph_StudyHard-Rg.woff2') format('woff2');
      font-weight: normal;
      font-style: normal;
  }
  .t1 {font-family: 'Ownglyph_StudyHard-Rg';}
  .completed {
      text-decoration: line-through;
      color: gray;
  }
  .category {
    margin-left: 50px;
    font-size: 0.9em;
    color: #003cff;
  }
  .date {
    margin-left: 50px;
    font-size: 0.9em;
    color: #f700ff;
  }
</style>