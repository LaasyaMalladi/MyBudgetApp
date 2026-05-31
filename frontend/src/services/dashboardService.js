import api from "./api";

export const getDashboard = (userId, month, year) => {
  return api.get(`/dashboard/user/${userId}`, {
    params: { month, year },
  });
};
