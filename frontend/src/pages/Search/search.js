import React, { useState, useContext, useEffect } from 'react';
import { Navigate, redirect, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../../services/AuthContext';

const Search = () => {
  const { hall, reel } = useParams()
  const { isLoggedIn } = useContext(AuthContext);
  const [title, setTitle] = useState('');
  const [contents, setContents] = useState('');
  const [files, setFiles] = useState([]);
  const navigate = useNavigate();
  const [query, setQuery] = useState('');
  const [results, setResults] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const response = await axios.get(
        `http://localhost:7531/api/search/hall?query=${query}`,
        {},
        { withCredentials: true }
      );
      console.log(response);
    };
    fetchData();
  }, [reel]);

  return (
    <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
    </div>
  );
}

export default Search;
