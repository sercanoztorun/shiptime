import React from 'react';
import './App.css';
import Home from "./pages/Home";
import Shipment from "./pages/Shipment";
import Success from "./pages/Success";
import {
    BrowserRouter as Router,
    Routes,
    Route,
    Navigate,
} from 'react-router-dom';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/home" element={<Home />} />
                <Route path="/" element={<Navigate replace to="/home" />} />
                <Route path="/shipment" element={<Shipment />} />
                <Route path="/success" element={<Success />} />
            </Routes>
        </Router>
    );
}

export default App;
