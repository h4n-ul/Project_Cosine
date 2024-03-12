import React, { useState } from "react";
import axios from "axios";

const Login = ({ setForm }) => {
  const [artistId, setArtistId] = useState('');
  const [password, setPassword] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/backend/artists/login",
        { artistId, password },
        { withCredentials: true }
      );
      window.location.reload();
    } catch (error) {
      console.error("Login failed:", error.response.data.message);
    }
  };

  return (
    <form onSubmit={handleLogin}>
      <div>
        <input 
          placeholder="ID/Email" 
          className="input input-bordered w-96" 
          value={artistId} 
          onChange={(e) => setArtistId(e.target.value)}
        />
      </div>
      <div>
        <input 
          placeholder="Password" 
          className="input input-bordered w-96" 
          type="password" 
          value={password} 
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <label className="label cursor-pointer">
        Remember me
        <input type="checkbox" className="toggle" />
      </label>
      <div className="w-96">
        <button type="button" className="btn btn-xs" onClick={()=>setForm('register')}>No account?</button>
        <button type="submit" className="btn btn-neutral">Login</button>
      </div>
    </form>
  );
}

export default Login;
