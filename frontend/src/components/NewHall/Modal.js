import React, { useState } from "react";

const NewHall = () => {
  const [hname, setHname] = useState()
  const [hdesc, setHdesc] = useState()
  const [hsrc, setHsrc] = useState()

  const handleCreateNewHall = () => {}

  return (
    <>
      <button className="btn btn-neutral" onClick={()=>document.getElementById('loginModal').showModal()}>Create new hall</button>
      <dialog id="loginModal" className="modal">
        <div className="card bg-base-200">
          <div className="card-body">
            <form method="dialog" onSubmit={handleCreateNewHall}>
              <button className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2">✕</button>
              <div>
                <input 
                  placeholder="Hall name" 
                  className="input input-bordered w-96" 
                  value={hname} 
                  onChange={(e) => setHname(e.target.value)}
                />
              </div>
              <div>
                <input 
                  placeholder="Hall description" 
                  className="input input-bordered w-96" 
                  value={hdesc} 
                  onChange={(e) => setHdesc(e.target.value)}
                />
              </div>
              <div>
                <input 
                  placeholder="Link" 
                  className="input input-bordered w-96" 
                  value={hsrc} 
                  onChange={(e) => setHsrc(e.target.value)}
                />
              </div>
              <div className="w-96">
                <button type="submit" className="btn btn-neutral">Create!</button>
              </div>
            </form>
          </div>
        </div>
      </dialog>
    </>
    
  );
}

export default NewHall;