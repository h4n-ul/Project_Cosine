import React, { useState } from "react";
import Login from "./Login";
import Register from "./Regist";

const Modal = () => {
  const [form, setForm] = useState('login');
  return (
    <>
      <button className="btn" onClick={()=>document.getElementById('loginModal').showModal()}>Login</button>
      <dialog id="loginModal" className="modal">
        <div className="card bg-base-200">
          <div className="card-body">
            <form method="dialog">
              <button className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
            </form>
            {form === 'login'?
            <Login setForm={setForm}/>:<Register setForm={setForm}/>}
          </div>
        </div>
      </dialog>
    </>

  );
}

export default Modal;