import React from 'react';

const Header = () => {
  const login = false
  return (
    <header style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
      <div className="logo" style={{margin: '20px'}}>Your Logo</div>
      <a href="/" style={{margin: '20px'}}>Home</a>
      <a href="/search" style={{margin: '20px'}}>Search</a>
      {login
      ?<><a href="/profile" style={{margin: '20px'}}>Profile</a><a href="/logout" style={{margin: '20px'}}>Logout</a></>
      :<a href="/login" style={{margin: '20px'}}>Login</a>
      }
    </header>
  );
};

export default Header;
