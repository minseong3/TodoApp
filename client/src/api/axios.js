import axios from 'axios';

// API 요청의 기본 URL을 설정하기 위해 axios 인스턴스 생성
const apiClient = axios.create({
    baseURL: 'http://localhost:8081', // 서버의 기본 URL
    headers: {
        'Content-Type': 'application/json', // 전송하는 데이터가 어떤 타입인지에 대한 설명
    },
});

// // 요청 로깅
// apiClient.interceptors.request.use((config) => {
//     console.log('Request:', config);
//     return config;
// }, (error) => {
//     console.error('Request Error:', error);
//     return Promise.reject(error);
// });
//
// // 응답 로깅
// apiClient.interceptors.response.use((response) => {
//     console.log('Response:', response);
//     return response;
// }, (error) => {
//     console.error('Response Error:', error.response.data || error);
//     return Promise.reject(error);
// });

export default apiClient;