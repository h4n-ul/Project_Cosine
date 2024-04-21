import React, { useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AudioCard from '../../components/AudioCard';

const Profile = () => {
  const { uid } = useParams()

  const [profile, setProfile] = useState(null);

  const getProfile = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/backend/artist/${uid}`, {
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
      const result = await getProfile();
      setProfile(result);
    };
    fetchData();
  }, [reel]);

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
          <p style={{marginLeft: '10px'}}>{reelInfo.owner}</p>
          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center', marginRight: '10px'}}>
            <p style={{fontWeight: '200', fontSize: '10px'}}>/b/{reelInfo.hallId}/{reelInfo.reelId}</p>
          </div>
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
        <div dangerouslySetInnerHTML={{__html: reelInfo.contents}}/>
      </div>
    </div>
  )
}

export default Profile;
