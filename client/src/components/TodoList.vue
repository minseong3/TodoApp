<template>
    <ul>
        <p class="t1" v-for="(item, index) in props.todoItems" :key="item.id">
            <span :class="{completed: item.completed}">{{ item.text }}</span>
            <button @click="completeTodo(item.id)">완료</button>
            <button @click="removeTodo(item.id, index)">삭제</button>
        </p>
    </ul>
</template>

<script setup>
  import { defineProps, defineEmits } from 'vue';

  // 부모 컴포넌트로부터 todoItems를 props로 받는다
  const props = defineProps({
    todoItems: Array,
  });

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
  button {
      background: linear-gradient(to right, #9fd8ff, #ff92aa);
      padding: 10px 15px 10px 15px;
      box-sizing: border-box;
      border-radius: 50px;
      min-width: 70px;
      height: auto;
      font-size: 16px;
      font-weight: 700;
      color: #000000;
      cursor: pointer;
      margin-left: 10px;
      box-shadow: 0px -3px 3px #ffffff, 0px 3px 3px #cccccc;
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
</style>