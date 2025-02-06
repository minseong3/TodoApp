import apiClient from '@/api/axios';

// 모든 Todo 목록 조회하는 API Request
export const fetchTodos = async () => {
  const response = await apiClient.get('/todos');
  return response.data;
};

// 새로운 Todo를 추가하는 API Request
export const addTodoItem = async (newTodo) => {
  const response = await apiClient.post('/todos', newTodo);
  return response.data;
};

// 특정 id Todo를 완료 처리하는 API Request
export const completeTodoItem = async (id, data) => {
  const response = await apiClient.post(`/todos/${id}/complete`, data);
  return response.data;
};

// 특정 id Todo를 삭제 처리하는 API Request
export const removeTodoItem = async (id) => {
  await apiClient.delete(`/todos/${id}`);
};

// keyword로 Todo를 검색하는 API Request
export const searchTodoItems = async (keyword) => {
  const response = await apiClient.get(`/todos/search`, {
    params: {keyword}
  });
  console.log("검색 결과:", response.data);
  return response.data;
};

// 모든 Todo를 삭제 처리하는 API Request
export const clearAllTodoItems = async () => {
  await apiClient.delete('/todos');
};

// 카테고리 별 Todo 목록 조회하는 API Request
export const filteredTodos = async (category) => {
  const response = await apiClient.get('/todos/filter', {
    params: { category }
  });
  return response.data;
};

// 랜덤 명언을 가져오는 API Request
export const getRandomQuote = async () => {
  const response = await apiClient.get('/quotes');
  return response.data;
};

export const updateTodoText = async (id, newText) => {
  await apiClient.post(`/todos/${id}/text`, {
    text: newText
  });
};