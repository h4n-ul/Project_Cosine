import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios'

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
  
    useEffect(() => {
      const checkLoginStatus = async () => {
        try {
          const response = await axios.get('http://localhost:8080/backend/artists/getLoginInfo', {
            withCredentials: true
          });
          const sessionId = response.data.sessionId;
          setIsLoggedIn(sessionId !== '');
        } catch (error) {
          console.error('Failed to check login status:', error);
        }
      };
  
      checkLoginStatus();
    }, []);
  
    return (
      <AuthContext.Provider value={{ isLoggedIn, setIsLoggedIn }}>
        {children}
      </AuthContext.Provider>
    );
  };