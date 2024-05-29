import React from 'react';
import { Link } from 'react-router-dom';

const Card = ({ hallid, hallname, contents, img }) => {
  return (
    <div className="card w-128 h-80 glass">
        <figure><img src={img} alt="hall image"/></figure>
        <div className="card-body">
            <h2 className="card-title">{hallname}</h2>
            <p>{contents}</p>
            <div className="card-actions justify-end">
            <Link className="btn" to={`/b/${hallid}`}>Redirect!</Link>
            </div>
        </div>
    </div>
  );
};

export default Card;
