import api from "./api";

export const getTransactions = (userId, month, year) => {
  const start = `${year}-${String(month).padStart(2, "0")}-01`;
  const daysInMonth = new Date(year, month, 0).getDate();
  const end = `${year}-${String(month).padStart(2, "0")}-${daysInMonth}`;
  return api.get(`/transactions/user/${userId}/month`, {
    params: { start, end },
  });
};

export const createTransaction = (transaction) => {
  return api.post("/transactions", transaction);
};

export const deleteTransaction = (transactionId) => {
  return api.delete(`/transactions/${transactionId}`);
};
