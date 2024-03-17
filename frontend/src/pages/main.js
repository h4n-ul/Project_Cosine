import React, { useContext } from 'react';
import '../tailwind.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from '../components/Header';
import Studio from './Studio/studio';
import NotFound from './NotFound/404';
import LoginPage from './Artist/login';
import Mixtape from './Mixtapes/mix';
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
        <Route path="/login" element={<LoginPage />}></Route>
        <Route path="/b/:hall" element={<Hall />}></Route>
        <Route path="/b/:hall/:mix" element={<Mixtape />}></Route>
        <Route path="/*" element={<NotFound />}></Route>
      </Routes>
      {data.streamId != '' ? 
      <footer style={{position: 'fixed', bottom: '0'}}><AudioPlayer streamId={data.streamId} /></footer> : <></>}
    </BrowserRouter>
  );
};

export default Main;