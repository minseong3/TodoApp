import apiClient from '@/api/axios';

export const fetchTodos = async () => {
  const response = await apiClient.get('/todos');
  return response.data;
};

export const addTodoItem = async (newTodo) => {
  const response = await apiClient.post('/todos', newTodo);
  return response.data;
};

export const completeTodoItem = async (id, data) => {
  const response = await apiClient.patch(`/todos/${id}/complete`, data);
  return response.data;
};

export const removeTodoItem = async (id) => {
  await apiClient.delete(`/todos/${id}`);
};

export const searchTodoItems = async (keyword) => {
  const response = await apiClient.get(`/todos/search`, {
    params: {keyword}
  });
  console.log("검색 결과:", response.data);
  return response.data;
}
export const clearAllTodoItems = async () => {
  await apiClient.delete('/todos');
};