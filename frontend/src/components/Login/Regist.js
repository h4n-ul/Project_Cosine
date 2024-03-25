import React, { useState } from "react";

const Register = ({ setForm }) => {
  const [artistId, setArtistId] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [passwordConfirm, setPasswordConfirm] = useState('');
  const [acceptTerms, setAcceptTerms] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 비밀번호와 비밀번호 재입력이 동일한지 확인
    if (password !== passwordConfirm) {
      alert('Passwords do not match.');
      return;
    }

    // 이용 약관 동의 확인
    if (!acceptTerms) {
      alert('You must accept the terms of use.');
      return;
    }

    const response = await fetch('http://localhost:8080/backend/artists/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ artistId, email, password }),
    });
    const data = await response.json();
    console.log(data)
    if (response.status == 200) {
      window.location.reload();
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <input 
          placeholder="ID" 
          className="input input-bordered w-96" 
          value={artistId} 
          onChange={(e) => setArtistId(e.target.value)}
        />
      </div>
      <div>
        <input 
          placeholder="Email" 
          className="input input-bordered w-96" 
          value={email} 
          onChange={(e) => setEmail(e.target.value)}
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
      <div>
        <input 
          placeholder="Re-enter Password" 
          className="input input-bordered w-96" 
          type="password"
          value={passwordConfirm} 
          onChange={(e) => setPasswordConfirm(e.target.value)}
        />
      </div>
      <label className="label cursor-pointer">
        Accept terms of use
        <input 
          type="checkbox" 
          className="toggle" 
          checked={acceptTerms} 
          onChange={(e) => setAcceptTerms(e.target.checked)}
        />
      </label>
      <div>
        <button type="button" className="btn btn-xs" onClick={()=>setForm('login')}>Already have account?</button>
        <button type="submit" className="btn btn-neutral">Register</button>
      </div>
    </form>
  );
}

export default Register;
