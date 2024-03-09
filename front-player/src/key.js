import React from 'react';
import './tailwind.css'
import { BrowserRouter, Routes, Route, useParams } from 'react-router-dom';
import AudioPlayer from './player/player';
import './App.css';

function PlayerWrapper() {
  const { audioId } = useParams();
  return <AudioPlayer streamId={audioId} />;
}

function Key() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/:audioId" element={<PlayerWrapper />} />
      </Routes>
    </BrowserRouter>
  );
}

export default Key;
