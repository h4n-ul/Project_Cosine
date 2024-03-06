import React, { useState } from "react";

const Login = ({ setForm }) => {
  const [artistId, setArtistId] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch('http://localhost:8080/backend/artists/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ artistId, password }),
    });
    const data = await response.json();
    console.log(data)
    if (data.sessionId) {
      document.cookie = `sessionId=${data.sessionId}; path=/`;
    } else {
      alert(data.message)
    }
  };  

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <input 
          placeholder="ID/Email" 
          className="input input-bordered w-100" 
          value={artistId} 
          onChange={(e) => setArtistId(e.target.value)}
        />
      </div>
      <div>
        <input 
          placeholder="Password" 
          className="input input-bordered w-100" 
          type="password" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <label className="label cursor-pointer">
        Remember me
        <input type="checkbox" className="toggle" />
      </label>
      <div className="w-100">
        <button type="button" className="btn btn-xs" onClick={()=>setForm('register')}>No account?</button>
        <button type="submit" className="btn btn-neutral">Login</button>
      </div>
    </form>
  );
}

export default Login;
