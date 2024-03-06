import React, { createContext, useState } from 'react';

const AudioContext = createContext();

const AudioProvider = ({ children }) => {
    const [audio, setAudio] = useState(new Audio());

    return (
        <AudioContext.Provider value={{ audio, setAudio }}>
            {children}
        </AudioContext.Provider>
    );
}

export default { AudioContext, AudioProvider };