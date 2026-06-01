import useDashboard from "../hooks/useDashboard";

function DashboardPage() {
  const { data, loading, error } = useDashboard(6, 6, 2026);
  if (loading) return <p className="text-center mt-10">Loading...</p>;
  if (error)
    return (
      <p className="text-red-500 text-center mt-10">
        Error loading dashboard: {error.message}
      </p>
    );
  const spentPercentage = Math.round(
    (data.totalSpent / data.availableBudget) * 100,
  );
  const progressColor =
    spentPercentage >= 90
      ? "bg-red-500"
      : spentPercentage >= 70
        ? "bg-yellow-500"
        : "bg-green-500";
  return (
    <div className="max-w-md mx-auto p-4 flex flex-col gap-3">
      <div className="flex justify-between items-center">
        <h1 className="text-lg font-medium">Hey, Laasya</h1>
        <span className="text-sm text-gray-500">June 2026</span>
      </div>

      <div className="bg-white rounded-xl border border-gray-200 p-4 shadow">
        <p className="text-xs text-gray-500 mb-1">Available to spend</p>
        <div className="flex justify-between items-baseline">
          <p className="text-3xl font-medium">₹{data.availableBudget}</p>
          <span
            className={`text-xs px-2 py-1 rounded-full ${spentPercentage >= 90 ? "bg-red-100 text-red-800" : spentPercentage >= 70 ? "bg-yellow-100 text-yellow-800" : "bg-green-100 text-green-800"}`}
          >
            {spentPercentage}%
          </span>
        </div>
        <p className="text-xs text-gray-500 mt-1">
          of ₹{data.expectedIncome} income - ₹{data.totalSaved} savings
        </p>
        <div className="mt-3">
          <div className="flex justify-between text-xs text-gray-500 mb-1">
            <span>₹{data.totalSpent} spent</span>
            <span>₹{data.availableBudget} left</span>
          </div>
          <div className="h-2 bg-gray-100 rounded-full overflow-hidden">
            <div
              className={`h-full rounded-full ${progressColor}`}
              style={{ width: `${Math.min(spentPercentage, 100)}%` }}
            />
          </div>
        </div>
      </div>

      <div className="grid grid-cols-2 gap-2">
        <div className="bg-gray-50 rounded-lg p-3">
          <p className="text-xs text-gray-500 mb-1">Days left</p>
          <p className="text-xl font-medium">{data.daysLeftInMonth}</p>
          <p className="text-xs text-gray-500">
            ₹
            {Math.round(
              data.remainingBudget / Math.max(data.daysLeftInMonth, 1),
            )}
            /day
          </p>
        </div>
        <div className="bg-gray-50 rounded-lg p-3">
          <p className="text-xs text-gray-500 mb-1">Saved this month</p>
          <p className="text-xs text-green-700">
            {Math.round((data.totalSaved / data.expectedIncome) * 100)}% of
            income
          </p>
        </div>
        <div className="bg-gray-50 rounded-lg p-3">
          <p className="text-xs text-gray-500 mb-1">CC due next month</p>
          <p className="text-xs text-red-600">₹{data.creditCardDue}</p>
        </div>
        <div className="bg-gray-50 rounded-lg p-3">
          <p className="text-xs text-gray-500 mb-1">Total spent</p>
          <p className="text-xl font-medium">₹{data.totalSpent}</p>
        </div>
      </div>

      {data.topCategories.length > 0 && (
        <div className="bg-white border-gray-200 p-4">
          <p className="text-sm font-medium mb-3">Top Categories</p>
          {data.topCategories.map((cat, index) => (
            <div key={index} className="flex items-center gap-2 mb-3">
              <div className="w-2 h-2 rounded-full bg-purple-600 flex-shrink-0" />
              <span className="text-sm flex-1">{cat.categoryName}</span>
              <div className="flex-1 h-1.5 bg-gray-100 rounded-full overflow-hidden">
                <div
                  className="h-full bg-purple-600 rounded-full"
                  style={{ width: `${Math.min(cat.percentage, 100)}%` }}
                />
              </div>
              <span className="text-xs text-gray-500 min-w-16 text-right">
                ₹{cat.totalSpent}
              </span>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}

export default DashboardPage;
