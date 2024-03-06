import React from 'react';

const Card = ({ hallid, hallname, contents, img }) => {
  const goto = () => window.location.href = `/b/${hallid}`
  return (
    <div className="card w-128 h-80 glass">
        <figure><img src={img} alt="hall image"/></figure>
        <div className="card-body">
            <h2 className="card-title">{hallname}</h2>
            <p>{contents}</p>
            <div className="card-actions justify-end">
            <button className="btn" onClick={goto}>Redirect!</button>
            </div>
        </div>
    </div>
  );
};

export default Card;
