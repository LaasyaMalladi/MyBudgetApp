const PAYMENT_COLORS = {
  UPI: "bg-blue-100 text-blue-800",
  CREDIT_CARD: "bg-red-100 text-red-800",
  DEBIT_CARD: "bg-green-100 text-green-800",
  CASH: "bg-yellow-100 text-yellow-800",
  AMAZON_PAY_LATER: "bg-purple-100 text-purple-800",
};

function TransactionCard({ transaction, onDelete }) {
  return (
    <div className="bg-white rounded-xl border border-gray-200 p-3 flex justify-between items-center">
      <div className="flex flex-col gap-0.5">
        <p className="text-sm font-medium">
          {transaction.merchant?.name ||
            transaction.description ||
            "No description"}
        </p>
        <p className="text-xs text-gray-400">
          {transaction.category?.name || "Uncategorized"}
        </p>
        <p className="text-xs text-gray-400">{transaction.date}</p>
      </div>
      <div className="flex flex-col items-end gap-1">
        <p className="text-sm font-medium text-red-500">
          -₹{transaction.amount}
        </p>
        <span
          className={`text-xs px-2 py-0.5 rounded-full ${PAYMENT_COLORS[transaction.paymentType] || "bg-gray-100 text-gray-600"}`}
        >
          {transaction.paymentType}
        </span>
        <button
          onClick={() => onDelete(transaction.id)}
          className="text-gray-300 hover:text-red-400 text-xs mt-0.5"
        >
          ✕
        </button>
      </div>
    </div>
  );
}

export default TransactionCard;
