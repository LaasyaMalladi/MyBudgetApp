import { useLocation, useNavigate } from "react-router-dom";

function BottomNav() {
  const navigate = useNavigate();
  const location = useLocation();

  const tabs = [
    { path: "/", icon: "🏠", label: "Home" },
    { path: "/transactions", icon: "💳", label: "Transactions" },
    { path: "/woaw", icon: "📊", label: "WoAW" },
    { path: "/savings", icon: "💰", label: "Savings" },
    { path: "/settings", icon: "⚙️", label: "Settings" },
  ];

  return (
    <div className="fixed bottom-0 left-0 right-0 bg-white border-t border-gray-200 flex justify-around max-w-md mx-auto">
      {tabs.map((tab) => (
        <button
          key={tab.path}
          onClick={() => navigate(tab.path)}
          className={`flex flex-col items-center gap-1 px-3 py-1 text-xs 
            ${location.pathname === tab.path ? "text-purple-600" : "text-gray-400"}`}
        >
          <span className="text-xl">{tab.icon}</span>
          {tab.label}
        </button>
      ))}
    </div>
  );
}

export default BottomNav;
