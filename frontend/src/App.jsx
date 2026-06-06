import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import DashboardPage from "./pages/DashboardPage";
import BottomNav from "./components/BottomNav";

function App() {
  return (
    <BrowserRouter>
      <div className="pb-16">
        <Routes>
          <Route path="/" element={<DashboardPage />} />
        </Routes>
      </div>
      <BottomNav />
    </BrowserRouter>
  );
}

export default App;
