import React, { useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AudioCard from '../../components/AudioCard';
import { AuthContext } from '../../services/AuthContext';

const Reel = () => {
  const { hall, reel } = useParams()
  const auth = useContext(AuthContext);

  const [reelInfo, setReel] = useState(null);

  const getReel = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/backend/reel/${reel}`, {
        withCredentials: true
      });
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

  if (!reelInfo) {
    return (
      <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
        <div className='prose' style={{width: '60%'}}>
          <h1 style={{margin: '10px', fontSize: '1.75rem', fontWeight: '900'}}>Loading...</h1>
          <div>
            <p style={{marginLeft: '10px'}}>Loading...</p>
            <p style={{fontWeight: '200', fontSize: '10px', marginLeft: '10px'}}>DR: 0 dB</p>
            <p style={{fontWeight: '200', fontSize: '10px', marginLeft: '10px'}}>Loading...</p>
          </div>
        </div>
      </div>
    )
  }

  return (
    <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
      <div className='prose' style={{width: '60%'}}>
        <h1 style={{margin: '10px', fontSize: '1.75rem', fontWeight: '900'}}>{reelInfo.title}</h1>
        <div>
          <p style={{marginLeft: '10px'}}>{reelInfo.owner}</p>
          <p style={{fontWeight: '200', fontSize: '10px', marginLeft: '10px'}}>DR: {reelInfo.dynamicRange/10} dB</p>
          <p style={{fontWeight: '200', fontSize: '10px', marginLeft: '10px'}}>/b/{reelInfo.hallId}/{reelInfo.reelId}</p>
        </div>
        {reelInfo.audio.length > 0 ? (
          <>
            <div className="carousel w-full rounded-box">
              {reelInfo.audio.map((audi, i) => (
                <div
                  key={`item${i + 1}`}
                  id={`item${i + 1}`}
                  className="carousel-item w-full"
                  style={{ width: '100%', display: 'flex', justifyContent: 'center' }}
                >
                  <AudioCard title={audi.title} artist={audi.artist} src={audi.fileId} />
                </div>
              ))}
            </div>
          </>
        ) : null}
        {reelInfo.owner === auth.artistId ? <><Link className='btn' style={{margin: '5px'}} to={`/workroom/${hall}/${reel}`}>Rework</Link><Link className='btn btn-error' style={{margin: '5px'}}>Incinerate</Link></> : null}
        <div dangerouslySetInnerHTML={{__html: reelInfo.contents}}/>
      </div>
    </div>
  )
}

export default Reel;
