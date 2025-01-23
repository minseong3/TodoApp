<template>
  <footer class="legend">
    <p>-{{ randomQuote.author }}-<br>
        {{ randomQuote.authorProfile }}<br>
        {{ randomQuote.message }}</p>
  </footer>
</template>

<script setup>
  import { ref, onMounted } from 'vue';
  import apiClient from '@/api/axios';

  const randomQuote = ref('');

  async function fetchRandomQuote() {
    try {
      const response = await apiClient.get('/quotes'); //서버에서 랜덤 명언 가져오기
      randomQuote.value = response.data;
    } catch (error) {
      console.error('Failed to fetch quote: ', error);
    }
  }
  // 컴포넌트 마운트 시 랜덤 명언 호출
  onMounted(() => {
    fetchRandomQuote();
  });
</script>

  
<style scoped>
  @font-face {
    font-family: 'establishRetrosansOTF';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2112@1.0/establishRetrosansOTF.woff') format('woff');
    font-weight: normal;
    font-style: normal;
  }
  .legend {
  font-family: 'establishRetrosansOTF';
  background-color: #f8f9fa;
  padding: 5px;
  text-align: center;
  font-size: 20px;
  color: #555;
  position: fixed;
  bottom: 0;
  width: 100%;
  border-top: 1px solid #ddd;
  }

</style>