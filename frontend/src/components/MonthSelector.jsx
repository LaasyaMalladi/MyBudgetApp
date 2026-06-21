const MONTHS = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

function MonthSelector({ month, year, onPrev, onNext }) {
  return (
    <div className="flex items-center justify-between">
      <button
        onClick={onPrev}
        className="text-gray-400 hover:text-gray-600 px-2 py-1"
      >
        {" "}
        ‹
      </button>
      <span className="text-sm font-medium">
        {MONTHS[month - 1]} {year}
      </span>
      <button
        onClick={onNext}
        className="text-gray-400 hover:text-gray-600 px-2 py-1"
      >
        ›
      </button>
    </div>
  );
}

export default MonthSelector;
