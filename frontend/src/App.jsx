import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import DashboardPage from "./pages/DashboardPage";
import TransactionsPage from "./pages/TransactionsPage";
import BottomNav from "./components/BottomNav";

function App() {
  return (
    <BrowserRouter>
      <div className="pb-16">
        <Routes>
          <Route path="/" element={<DashboardPage />} />
          <Route path="/transactions" element={<TransactionsPage />} />
        </Routes>
      </div>
      <BottomNav />
    </BrowserRouter>
  );
}

export default App;
