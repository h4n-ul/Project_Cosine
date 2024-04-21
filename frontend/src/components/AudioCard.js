import React, { useContext, useEffect, useState } from 'react';
import { StreamProvider, StreamContext } from '../services/StreamContext';

const AudioCard = ({ title, artist, image, src }) => {
  const { setStreamId, setTitle, setArtist, setAlbum } = useContext(StreamContext);
  const handlePlay = () => {
    setStreamId(src)
    setTitle(title)
    setArtist(artist)
  }

  return (
    <div className="card w-full bg-base-100 shadow-xl" style={{}}>
      <div className="card-body">
        <h2 className="card-title">{title}</h2>
        <p>{artist}</p>
        <div className="card-actions justify-end">
          <button className="btn btn-neutral" onClick={handlePlay(src)}>Play</button>
        </div>
      </div>
    </div>
  )
}

export default AudioCard;