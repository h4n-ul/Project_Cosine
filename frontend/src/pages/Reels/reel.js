import React, { useContext, useEffect, useState } from 'react';
import ReactMarkdown from 'react-markdown';
import { useParams } from 'react-router-dom';
import remarkGfm from 'remark-gfm'
import { StreamProvider, StreamContext } from '../../services/StreamContext';
import axios from 'axios';
import { Link } from 'react-router-dom';

const Reel = () => {
  const { hall, reel } = useParams()

  const [reelInfo, setReel] = useState(null);

  const getReel = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/backend/reel/${reel}`, {
        withCredentials: true
      });
      console.log(response);
      return response.data;
    } catch (error) {
      console.error('Failed to fetch data:', error);
      return null;
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      const result = await getReel();
      setReel(result);
    };
    fetchData();
  }, [reel]);

  const { setStreamId } = useContext(StreamContext);

  const handleStreamIdChange = (newStreamId) => {
    setStreamId(newStreamId);
  };

  if (!reelInfo) {
    return (
      <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
        <div className='prose' style={{width: '60%'}}>
          <h1 style={{margin: '10px', fontSize: '1.5rem', fontWeight: '900'}}>Loading...</h1>
          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
            <p style={{marginLeft: '10px'}}>Loading...</p>
            <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center', marginRight: '10px'}}>
              <p style={{fontWeight: '200', fontSize: '10px'}}>Loading...</p>
            </div>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
      <div className='prose' style={{width: '60%'}}>
        <h1 style={{margin: '10px', fontSize: '1.5rem', fontWeight: '900'}}>{reelInfo.title}</h1>
        <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
          <p style={{marginLeft: '10px'}}>{reelInfo.owner.artistId}</p>
          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center', marginRight: '10px'}}>
            <p style={{fontWeight: '200', fontSize: '10px'}}>{reelInfo.link}</p>
          </div>
        </div>
        <div dangerouslySetInnerHTML={{__html: reelInfo.contents}}/>
      </div>
    </div>
  )
}

export default Reel;
