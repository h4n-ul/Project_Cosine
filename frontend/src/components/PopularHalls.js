import React, { useEffect, useState } from "react";
import Card from "./Card";
import axios from 'axios';

const PopularHalls = () => {

  const [halls, setHalls] = useState([{}]);
  const [cards, setCards] = useState([{}]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:7531/api/hall/popular`, {
          withCredentials: true
        });
        console.log(response.data);
        setCards(response.data.halls)
      } catch (error) {
        console.error('Failed to fetch data:', error);
      }
    };
    fetchData();
  }, []);

  return (
    <div className="carousel carousel-center max-w-md p-4 space-x-4 rounded-box">
      {cards.map((card) => (
        <div className="carousel-item"><Card hallid={card.src} hallname={card.title} contents={card.description} img={card.img} /></div>
      ))}
    </div>
  );
}

export default PopularHalls;
