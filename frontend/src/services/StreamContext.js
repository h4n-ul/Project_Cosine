import React, { createContext, useState } from 'react';

export const StreamContext = createContext();

export const StreamProvider = ({ children }) => {
  const [streamId, setStreamId] = useState('1');

  return (
    <StreamContext.Provider value={{ streamId, setStreamId }}>
      {children}
    </StreamContext.Provider>
  );
};