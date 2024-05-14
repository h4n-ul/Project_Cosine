import React, { createContext, useState, useEffect } from 'react';
import axios from 'axios'

export const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [artistId, setArtistId] = useState();
  
    useEffect(() => {
      const checkLoginStatus = async () => {
        try {
          const response = await axios.get('http://localhost:8080/backend/artist/getLoginInfo', {
            withCredentials: true
          });
          setIsLoggedIn(response.data.sessionId !== '');
          setArtistId(response.data.artistId);
        } catch (error) {}
      };
  
      checkLoginStatus();
    }, []);
  
    return (
      <AuthContext.Provider value={{ isLoggedIn, artistId, setIsLoggedIn, setArtistId }}>
        {children}
      </AuthContext.Provider>
    );
  };