import React, { useState, useEffect, useContext } from 'react';
import Modal from './Login/Modal';
import axios from 'axios';
import NewHall from './NewHall/Modal';
import { Link } from 'react-router-dom';
import { AuthContext } from '../services/AuthContext'

const Header = () => {
  const { isLoggedIn, setIsLoggedIn } = useContext(AuthContext);

  const handleLogout = async () => {
    try {
      await axios.post(
        "http://localhost:8080/backend/artists/logout",
        {},
        { withCredentials: true }
      );
      setIsLoggedIn(false)
      window.location.reload();
    } catch (error) {
      console.error("Logout failed:", error.response.data.message);
    }
  };

  return (
    <header style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
      <div className="logo" style={{margin: '20px'}}>Your Logo</div>
      <Link to="/" style={{margin: '20px'}}>Home</Link>
      <Link to="/search" style={{margin: '20px'}}>Search</Link>
      {isLoggedIn
      ?<><Link to="/profile" style={{margin: '20px'}}>Profile</Link><button className='btn' style={{margin: '20px'}} onClick={handleLogout}>Logout</button><NewHall/></>
      :<Modal/>
      }
    </header>
  );
};

export default Header;
