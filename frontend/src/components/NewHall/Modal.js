import React, { useState } from "react";
import { redirect } from 'react-router-dom';
import axios from 'axios';

const NewHall = () => {
  const [hname, setHname] = useState()
  const [hdesc, setHdesc] = useState()
  const [hsrc, setHsrc] = useState()

  const handleCreateNewHall = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(
        "http://localhost:8080/backend/hall/create",
        { hallName: hname, description: hdesc, src: hsrc },
        { withCredentials: true }
      );
      return redirect(`/b/${hsrc}`)
    } catch (error) {
      console.error("Login failed:", error.response.data.message);
    }
  };

  return (
    <>
      <button className="btn btn-neutral" onClick={()=>document.getElementById('newHallModal').showModal()}>Create new hall</button>
      <dialog id="newHallModal" className="modal">
        <div className="card bg-base-200">
          <div className="card-body">
            <form method="dialog">
              <button className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
              <form onSubmit={handleCreateNewHall}>
                <div className="form-control">
                  <label className="label">
                    <span className="label-text">Hall name</span>
                  </label>
                  <input
                    placeholder="Hall name"
                    className="input input-bordered w-96"
                    value={hname} 
                    onChange={(e) => setHname(e.target.value)}
                  />
                </div>
                <div className="form-control">
                  <label className="label">
                    <span className="label-text">Description</span>
                  </label>
                  <input
                    placeholder="Hall description"
                    className="input input-bordered w-96"
                    value={hdesc} 
                    onChange={(e) => setHdesc(e.target.value)}
                  />
                </div>
                <div className="form-control">
                  <label className="label">
                    <span className="label-text">Hall link</span>
                  </label>
                  <input
                    placeholder="Link"
                    className="input input-bordered w-96"
                    value={hsrc} 
                    onChange={(e) => setHsrc(e.target.value)}
                  />
                </div>
                <div className="w-96 modal-action">
                  <button type="submit" className="btn btn-neutral">Create!</button>
                </div>
              </form>
            </form>
          </div>
        </div>
      </dialog>
    </>
    
  );
}

export default NewHall;