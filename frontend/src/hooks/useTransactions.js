import { useState, useEffect, useCallback } from "react";
import { getTransactions } from "../services/transactionService";

function useTransactions(userId, month, year) {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchTransactions = useCallback(async () => {
    try {
      setLoading(true);
      const response = await getTransactions(userId, month, year);
      console.log("URL params:", userId, month, year);
      console.log("Response:", response.data);
      setData(response.data);
    } catch (err) {
      console.log("Error:", err);
      setError(err.message);
    } finally {
      setLoading(false);
    }
  }, [userId, month, year]);

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    fetchTransactions();
  }, [fetchTransactions]);

  return { data, loading, error, refetch: fetchTransactions };
}

export default useTransactions;
