import React, { createContext, useState } from 'react';

export const StreamContext = createContext();

export const StreamProvider = ({ children }) => {
  const [streamId, setStreamId] = useState(null);
  const [title, setTitle] = useState(null);
  const [artist, setArtist] = useState(null);
  const [album, setAlbum] = useState(null);

  return (
    <StreamContext.Provider value={{ streamId, title, artist, album, setStreamId, setTitle, setArtist, setAlbum }}>
      {children}
    </StreamContext.Provider>
  );
};