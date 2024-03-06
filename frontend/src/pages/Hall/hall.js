import React from 'react';
import { useParams } from 'react-router-dom';

const Hall = () => {
  const { hall } = useParams()
  return (
    <div>
      hallid: {hall}
    </div>
  );
}

export default Hall;
