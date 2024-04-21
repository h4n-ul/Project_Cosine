import React, { useContext } from 'react';
import '../tailwind.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from '../components/Header';
import Studio from './Studio/studio';
import NotFound from './NotFound/404';
import Profile from './Artist/profile';
import Reel from './Reels/reel';
import Hall from './Hall/hall';
import Workroom from './Workroom/workroom';
import { StreamProvider, StreamContext } from '../services/StreamContext';
import AudioPlayer from './Player';

const Main = () => {
  return (
    <StreamProvider>
      <MainContents />
    </StreamProvider>
  );
};

const MainContents = () => {
  const data = useContext(StreamContext);
  console.log(data); // { streamId, setStreamId } 또는 undefined

  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/workroom/:hall" element={<Workroom />}></Route>
        <Route path="/" element={<Studio />}></Route>
        <Route path="/profile/:uid" element={<Profile />}></Route>
        <Route path="/b/:hall" element={<Hall />}></Route>
        <Route path="/b/:hall/:reel" element={<Reel />}></Route>
        <Route path="/*" element={<NotFound />}></Route>
      </Routes>
      {data.streamId != null ? 
      <footer style={{position: 'fixed', bottom: '0', alignSelf: 'center'}}><AudioPlayer streamId={data.streamId} title={data.title} artist={data.artist} /></footer> : <></>}
    </BrowserRouter>
  );
};

export default Main;