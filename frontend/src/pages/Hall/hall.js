import React from 'react';
import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Hall = () => {
  const { hall } = useParams()
  const getHall = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/backend/hall/${hall}`, {
        withCredentials: true
      });
      console.log(response)
      return response
    } catch (error) {
      console.error('Failed to check login status:', error);
      return false;
    }
  }

  return (
    <div>
      {hall}
      {getHall()}
    </div>
  );
}

export default Hall;
