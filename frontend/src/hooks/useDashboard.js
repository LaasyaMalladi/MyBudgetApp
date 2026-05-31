import { useState, useEffect } from "react";
import { getDashboard } from "../services/dashboardService";

function useDashboard(userId, month, year) {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  useEffect(() => {
    const fetchDashboard = async () => {
      try {
        setLoading(true);
        const response = await getDashboard(userId, month, year);
        setData(response.data);
      } catch (err) {
        setError(err);
      } finally {
        setLoading(false);
      }
    };

    fetchDashboard();
  }, [userId, month, year]);
  return { data, loading, error };
}

export default useDashboard;
