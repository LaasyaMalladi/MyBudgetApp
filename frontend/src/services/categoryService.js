import api from "./api";

export const getCategories = async (userId) => {
  return api.get(`/categories/user/${userId}`);
};

export const createCategory = (category) => {
  return api.post("/categories", category);
};
