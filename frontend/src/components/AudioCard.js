import React, { useContext, useEffect, useState } from 'react';
import { StreamProvider, StreamContext } from '../services/StreamContext';

const AudioCard = ({ title, artist, image, src }) => {
  const stream = useContext(StreamContext);
  const handlePlay = () => {
    stream.setStreamId(src)
    stream.setTitle(title)
    stream.setArtist(artist)
  }

  if (stream.streamId === null) {
    handlePlay()
  }

  return (
    <div className="card w-full bg-base-100 shadow-xl" style={{}}>
      <div className="card-body">
        <h2 className="card-title">{title}</h2>
        <p>{artist}</p>
        <div className="card-actions justify-end">
          <button className="btn btn-neutral" onClick={() => handlePlay(src)}>Play</button>
        </div>
      </div>
    </div>
  )
}

export default AudioCard;