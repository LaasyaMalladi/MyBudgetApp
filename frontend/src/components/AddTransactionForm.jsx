import { useState } from "react";
import { createTransaction } from "../services/transactionService";

function AddTransactionForm({ userId, onSuccess, onCancel }) {
  const [form, setForm] = useState({
    amount: "",
    date: new Date().toISOString().split("T")[0],
    description: "",
    paymentType: "UPI",
  });
  const [saving, setSaving] = useState(false);

  const handleSubmit = async () => {
    if (!form.amount) return;
    try {
      setSaving(true);
      await createTransaction({
        user: { id: userId },
        amount: parseFloat(form.amount),
        date: form.date,
        description: form.description,
        paymentType: form.paymentType,
      });
      onSuccess();
    } catch (err) {
      console.error("Failed to create transaction", err);
    } finally {
      setSaving(false);
    }
  };

  return (
    <div className="bg-white rounded-xl border border-gray-200 p-4 flex flex-col gap-4">
      <p className="text-sm font-medium">New Transaction</p>
      <input
        type="number"
        placeholder="Amount ₹"
        value={form.amount}
        onChange={(e) => setForm({ ...form, amount: e.target.value })}
        className="border border-gray-200 rounded-lg py-2 pl-2 text-sm w-full"
      />
      <input
        type="date"
        value={form.date}
        onChange={(e) => setForm({ ...form, date: e.target.value })}
        className="border border-gray-200 rounded-lg p-2 pl-2 text-sm w-full"
      />
      <input
        type="text"
        placeholder="Description (optional)"
        value={form.description}
        onChange={(e) => setForm({ ...form, description: e.target.value })}
        className="border border-gray-200 rounded-lg py-2 pl-2 text-sm w-full"
      />
      <select
        value={form.paymentType}
        onChange={(e) => setForm({ ...form, paymentType: e.target.value })}
        className="border border-gray-200 rounded-lg p-2 text-sm w-full"
      >
        <option value="UPI">UPI</option>
        <option value="CREDIT_CARD">Credit Card</option>
        <option value="CASH">Cash</option>
        <option value="AMAZON_PAY_LATER">Amazon Pay Later</option>
        <option value="DEBIT_CARD">Debit Card</option>
      </select>

      <div className="flex gap-2">
        <button
          onClick={handleSubmit}
          disabled={saving}
          className="flex-1 bg-purple-600 text-white py-2 rounded-lg text-sm disabled:opacity-50"
        >
          {saving ? "Saving..." : "Save"}
        </button>
        <button
          onClick={onCancel}
          className="flex-1 border border-gray-200 rounded-lg text-sm"
        >
          Cancel
        </button>
      </div>
    </div>
  );
}

export default AddTransactionForm;
