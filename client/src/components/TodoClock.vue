<template>
    <div>
        <p>{{ date }}<br>
        {{ampm}} {{ time }}</p>
    </div>
</template>

<script>
// Options API -> Composition API로 변경
import { ref, onMounted } from 'vue';

export default {
  setup() {
    const ampm = ref('');
    const time = ref('');
    const date = ref('');

    function getNow() {
      const now = new Date();
      
      ampm.value = now.getHours() >= 12 ? 'PM' : 'AM';

      time.value = `${String(now.getHours() % 12 || 12).padStart(2, '0')}:${String(now.getMinutes()).padStart(2, '0')}:${String(now.getSeconds()).padStart(2, '0')}`;

      date.value = `${now.getFullYear()}-${(now.getMonth() + 1).toString().padStart(2, '0')}-${now.getDate().toString().padStart(2, '0')}`;
    }

    onMounted(() => {
      getNow();
      setInterval(getNow, 1000);
    });

    return {ampm, time, date};
  }
}
// export default {
//   components: {},
//   data() {
//     return {
//       ampm: '',
//       time: '',
//       date: ''
//     }
//   },
//   created() {
//     setInterval(() => this.getNow(), 1000)
//   },
//   methods: {
//     getNow() {
//       const date = new Date()
//       // 오전, 오후(AM, PM) 표시
//       this.ampm = date.getHours() >= 12 ? 'PM' : 'AM'
      
//       // 시분초(00:00:00) 표시 || 쓰는 이유 : 12, 24 는 falsy한 값이기 때문
//       this.time = `${String(date.getHours() % 12 || 12).padStart(2, '0')}:${String(
//         date.getMinutes()
//       ).padStart(2, '0')}:${String(date.getSeconds()).padStart(2, '0')}`
      
//       // 년월일(yyyy-mm-dd) 표시
//       this.date = `${date.getFullYear()}-${(date.getMonth() + 1)
//         .toString()
//         .padStart(2, 0)}-${date.getDate().toString().padStart(2, 0)}`
//     }
//   }
// }
</script>

<style scoped>
    p {
        width: 165px; /* 너비 */
        height: 80px;
        transform: translateX(850px);
        text-align: center;
        font-family: 'Ownglyph_StudyHard-Rg';
        font-size: 35px;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
        border-radius: 15px;
        background: rgba(255, 145, 0, 0.904); /* 투명한 배경 */
        box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3); /* 그림자 */
        border: 5px solid #000000;
    }
    @font-face {
    font-family: 'Ownglyph_StudyHard-Rg';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/2411-3@1.0/Ownglyph_StudyHard-Rg.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
</style>