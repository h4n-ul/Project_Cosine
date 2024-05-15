import React, { useContext, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';
import AudioCard from '../../components/AudioCard';
import { AuthContext } from '../../services/AuthContext';

const Profile = () => {
  const { uid } = useParams()
  const auth = useContext(AuthContext);

  const [profile, setProfile] = useState(null);

  const getProfile = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/backend/artist/${uid}`, {
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
      const result = await getProfile();
      setProfile(result);
    };
    fetchData();
  }, []);

  if (!profile) {
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
        <h1 style={{margin: '10px', fontSize: '1.5rem', fontWeight: '900'}}>{profile.artistNname}</h1>
        <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
          <p style={{marginLeft: '10px'}}>{profile.description}</p>
          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center', marginRight: '10px'}}>
            <p style={{fontWeight: '200', fontSize: '10px'}}>/u/{profile.artistId}</p>
          </div>
        </div>
        {/* {reelInfo.audio.length > 0 ? (
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
        <div dangerouslySetInnerHTML={{__html: reelInfo.contents}}/> */}
        {auth.artistId === profile.artistId ? <Link className="btn" to={`/u/${profile.artistId}/edit`}>Edit Profile</Link> : null}
      </div>
    </div>
  )
}

export default Profile;
