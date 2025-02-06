<template>
    <div class="container">
        <p>{{ date }}<br>
          {{ampm}} {{ time }}</p>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';

const ampm = ref('');
const time = ref('');
const date = ref('');

function getNow() {
  const now = new Date();
      
  ampm.value = now.getHours() >= 12 ? 'PM' : 'AM';
  // || 쓰는 이유 : 12, 24 는 falsy한 값이기 때문
  time.value = `${String(now.getHours() % 12 || 12).padStart(2, '0')}:${String(now.getMinutes()).padStart(2, '0')}:${String(now.getSeconds()).padStart(2, '0')}`;

  date.value = `${now.getFullYear()}-${(now.getMonth() + 1).toString().padStart(2, '0')}-${now.getDate().toString().padStart(2, '0')}`;
}

onMounted(() => {
  getNow();
  setInterval(getNow, 1000);
});
</script>

<style scoped>
    .container {
      display: flex;
      justify-content: center; /* 가로 중앙 정렬 */
      align-items: center; /* 세로 중앙 정렬 */
    }
    p {
        width: 165px; /* 너비 */
        height: 80px;
        text-align: center;
        font-family: 'Ownglyph_StudyHard-Rg';
        font-size: 35px;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 15px;
        border-radius: 20px;
        background: rgba(173, 239, 118, 0.9); /* 투명한 배경 */
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3); /* 그림자 */
        border: 5px solid #b3c3b7;
    }
    @font-face {
    font-family: 'Ownglyph_StudyHard-Rg';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2411-3@1.0/Ownglyph_StudyHard-Rg.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
</style>