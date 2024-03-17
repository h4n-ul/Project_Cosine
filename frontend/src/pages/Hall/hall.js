import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { Link } from 'react-router-dom';

const Hall = () => {
  const { hall } = useParams();
  const [hallInfo, setHallInfo] = useState(null);

  const getHall = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/backend/hall/${hall}`, {
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
      const result = await getHall();
      setHallInfo(result);
    };
    fetchData();
  }, [hall]);

  if (!hallInfo) {
    return <div>Loading...</div>;
  }

  return (
    <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
      <div className='prose' style={{width: '60%'}}>
        <h1 style={{margin: '10px', fontSize: '1.5rem', fontWeight: '900'}}>{hallInfo.title}</h1>
        <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'space-between', alignItems: 'center'}}>
          <p style={{marginLeft: '10px'}}>{hallInfo.description}</p>
          <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center', marginRight: '10px'}}>
            <p style={{fontWeight: '200'}}>매니저: {hallInfo.managerName}</p>
          </div>
        </div>
        <div>
          <ul>
            {hallInfo.mixtapes?.map((mixtape) => (
              <li key={mixtape.id}>{mixtape.name}</li>
            ))}
          </ul>
          <Link className="btn btn-circle" to={`/workroom/${hall}`}>
            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M12 6v12m6-6H6" />
            </svg>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Hall;
