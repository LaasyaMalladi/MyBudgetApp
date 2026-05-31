import useDashboard from "../hooks/useDashboard";

function DashboardPage() {
  const { data, loading, error } = useDashboard(6, 6, 2026);
  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error loading dashboard: {error.message}</p>;
  return (
    <div>
      <h1>My Budget App</h1>
      <p>Expected income: ₹{data.expectedIncome}</p>
      <p>Total Saved: ₹{data.totalSaved}</p>
      <p>Available Budget: ₹{data.availableBudget}</p>
      <p>Total Spent: ₹{data.totalSpent}</p>
      <p>Remaining Budget: ₹{data.remainingBudget}</p>
      <p>Days left in the month: {data.daysLeftInMonth}</p>
    </div>
  );
}

export default DashboardPage;
