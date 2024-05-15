import React, { useState, useEffect, useContext } from 'react';
import Modal from './Login/Modal';
import axios from 'axios';
import NewHall from './NewHall/Modal';
import { Link } from 'react-router-dom';
import { AuthContext } from '../services/AuthContext'

const Header = () => {
  const auth = useContext(AuthContext);

  const handleLogout = async () => {
    try {
      await axios.post(
        "http://localhost:8080/backend/artist/logout",
        {},
        { withCredentials: true }
      );
      auth.setIsLoggedIn(false)
      window.location.reload();
    } catch (error) {
      console.error("Logout failed:", error.response.data.message);
    }
  };

  const handleSearch = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/backend/search",
        {},
        { withCredentials: true }
      );
      console.log(response);
    } catch (error) {
      console.error("Search failed:", error.response.data.message);
    }
  };

  return (
    <header style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
      <Link to="/" style={{margin: '20px', fontWeight: '800'}}>Project Cosine</Link>
      <div className="dropdown"  style={{margin: '20px'}}>
        <div tabIndex={0} role="button" className="m-1">Search</div>
        <div tabIndex={0} className="dropdown-content z-[1] card card-compact w-64 p-2 shadow ">
          <form onSubmit={handleSearch}>
            <input type="text" placeholder="Type here" className="input w-full max-w-xs" />
          </form>
        </div>
      </div>
      {auth.isLoggedIn
      ?<><Link to={`/u/${auth.artistId}`} style={{margin: '20px'}}>Profile</Link><button className='btn' style={{margin: '20px'}} onClick={handleLogout}>Logout</button><NewHall/></>
      :<Modal/>
      }
    </header>
  );
};

export default Header;
