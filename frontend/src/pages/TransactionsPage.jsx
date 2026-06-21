import { useState } from "react";
import useTransactions from "../hooks/useTransactions";
import { deleteTransaction } from "../services/transactionService";
import AddTransactionForm from "../components/AddTransactionForm";
import TransactionCard from "../components/TransactionCard";
import MonthSelector from "../components/MonthSelector";

function TransactionsPage() {
  const [month, setMonth] = useState(6);
  const [year, setYear] = useState(2026);
  const [showForm, setShowForm] = useState(false);

  const { data, loading, error, refetch } = useTransactions(6, month, year);
  console.log("TransactionsPage rendered", month, year);
  const handlePrev = () => {
    if (month === 1) {
      setMonth(12);
      setYear(year - 1);
    } else {
      setMonth(month - 1);
    }
  };

  const handleNext = () => {
    if (month === 12) {
      setMonth(1);
      setYear(year + 1);
    } else {
      setMonth(month + 1);
    }
  };

  const handleDelete = async (id) => {
    await deleteTransaction(id);
    refetch();
  };

  if (loading)
    return <p className="text-center mt-10 text-gray-500">Loading...</p>;
  if (error)
    return <p className="text-red-500 text-center mt-10">Error: {error}</p>;

  return (
    <div className="max-w-md mx-auto p-4 flex flex-col gap-3">
      <div className="flex justify-between items-center">
        <h1 className="text-lg font-medium">Transactions</h1>
        <button
          onClick={() => setShowForm(!showForm)}
          className="text-sm bg-purple-600 text-white px-3 py-1.5 rounded-lg"
        >
          + Add
        </button>
      </div>

      {showForm && (
        <AddTransactionForm
          userId={6}
          onSuccess={() => {
            setShowForm(false);
            refetch();
          }}
          onCancel={() => setShowForm(false)}
        />
      )}

      <MonthSelector
        month={month}
        year={year}
        onPrev={handlePrev}
        onNext={handleNext}
      />
      {data.length === 0 ? (
        <p className="text-sm text-gray-400 mt-10">
          No transactions for this month
        </p>
      ) : (
        <div className="flex flex-col gap-2">
          {data.map((transaction) => (
            <TransactionCard
              key={transaction.id}
              transaction={transaction}
              onDelete={handleDelete}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default TransactionsPage;
