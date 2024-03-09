import React, { useState, useEffect } from 'react';
import Modal from './Login/Modal';
import axios from 'axios';

const Header = () => {
  const checkLoginStatus = async () => {
    try {
      const response = await axios.get('http://localhost:8080/backend/artists/getLoginInfo', {
        withCredentials: true
      });
  
      const sessionId = response.data;
      console.log(sessionId)
      return sessionId !== '';
    } catch (error) {
      console.error('Failed to check login status:', error);
      return false;
    }
  }

  const [isLoggedIn, setIsLoggedIn] = useState(false);

  const handleLogout = async (e) => {
    e.preventDefault();
    const response = await fetch('http://localhost:8080/backend/artists/logout', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ /* 필요한 데이터를 전송하세요 */ }),
      withCredentials: true
    });

    if (response.ok) { // 로그아웃 요청이 성공했을 경우
      document.cookie = "JSESSIONID=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      window.location.reload();
    } else {
      alert('Logout failed') // 실패했을 경우에 대한 처리를 추가하세요
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
      ?<><a href="/profile" style={{margin: '20px'}}>Profile</a><button className='btn' style={{margin: '20px'}} onClick={handleLogout}>Logout</button></>
      :<Modal/>
      }
    </header>
  );
};

export default Header;
