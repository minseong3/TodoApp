import apiClient from '@/api/axios';

export const fetchTodos = async () => {
  const response = await apiClient.get('/todos');
  return response.data;
};

export const addTodoItem = async (newTodo) => {
  const response = await apiClient.post('/todos', newTodo);
  return response.data;
};

export const completeTodoItem = async (id) => {
  const response = await apiClient.patch(`/todos/${id}/complete`);
  return response.data;
};

export const removeTodoItem = async (id) => {
  await apiClient.delete(`/todos/${id}`);
};

export const clearAllTodoItems = async () => {
  await apiClient.delete('/todos');
};