import React, { createContext, useState } from 'react';
import '../tailwind.css'
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Header from '../components/Header';
import Studio from './Studio/studio';
import NotFound from './NotFound/404';
import LoginPage from './Artist/login';
import AudioPlayer from '../services/Stream';
import Mixtape from './Mixtapes/mix';
import Hall from './Hall/hall';
import Workroom from './Workroom/workroom';

const StreamIdContext = createContext();

export function StreamIdProvider({ children }) {
  const [streamId, setStreamId] = useState(null);
  return (
      <StreamIdContext.Provider value={{ streamId, setStreamId }}>
          {children}
      </StreamIdContext.Provider>
  );
}

const Main = () => {
  return (
    <BrowserRouter>
      <Header/>
      <Routes>
        <Route path="/b/:hall/workroom" element={<Workroom/>}></Route>
        <Route path="/" element={<Studio/>}></Route>
        <Route path="/login" element={<LoginPage/>}></Route>
        <Route path="/b/:hall" element={<Hall/>}></Route>
        <Route path="/b/:hall/:mix" element={<Mixtape/>}></Route>
        {/* <Route path="/product/*" element={<Product />}></Route> */}
        {/* 상단에 위치하는 라우트들의 규칙을 모두 확인, 일치하는 라우트가 없는경우 처리 */}
        <Route path="/*" element={<NotFound />}></Route>
      </Routes>
      <AudioPlayer streamId={1}/>
    </BrowserRouter>
  );
}

export default Main;