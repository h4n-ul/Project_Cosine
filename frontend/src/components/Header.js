import React, { useState, useEffect } from 'react';
import Modal from './Login/Modal';
import axios from 'axios';
import NewHall from './NewHall/Modal';

const Header = () => {
  const checkLoginStatus = async () => {
    try {
      const response = await axios.get('http://localhost:8080/backend/artists/getLoginInfo', {
        withCredentials: true
      });
  
      const sessionId = response.data.sessionId;
      return sessionId !== '';
    } catch (error) {
      console.error('Failed to check login status:', error);
      return false;
    }
  }

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogout = async () => {
    try {
      await axios.post(
        "http://localhost:8080/backend/artists/logout",
        {},
        { withCredentials: true }
      );
      window.location.reload();
    } catch (error) {
      console.error("Logout failed:", error.response.data.message);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      const result = await checkLoginStatus();
      setIsLoggedIn(result);
    };
    fetchData();
  }, []);

  return (
    <header style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
      <div className="logo" style={{margin: '20px'}}>Your Logo</div>
      <a href="/" style={{margin: '20px'}}>Home</a>
      <a href="/search" style={{margin: '20px'}}>Search</a>
      {isLoggedIn
      ?<><a href="/profile" style={{margin: '20px'}}>Profile</a><button className='btn' style={{margin: '20px'}} onClick={handleLogout}>Logout</button><NewHall/></>
      :<Modal/>
      }
    </header>
  );
};

export default Header;
