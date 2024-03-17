import React, { useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../../services/AuthContext';

const Workroom = () => {
  const { hall } = useParams()
  const { isLoggedIn } = useContext(AuthContext);
  const [title, setTitle] = useState('');
  const [contents, setContents] = useState('');
  const [markdown, setMarkdown] = useState(false);
  const [files, setFiles] = useState([]);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('title', title);
    formData.append('contents', contents);
    files.forEach((file, index) => {
      formData.append(`files[${index}]`, file, file.name);
    });
  
    // formData를 서버로 전송하는 코드
    const response = await axios.post('http://localhost:8080/backend/mixtape/create', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      withCredentials: true // 쿠키를 요청과 함께 보냅니다.
    });
  };  

  const handleFileChange = (e) => {
    setFiles(Array.from(e.target.files)); // FileList를 배열로 변환
  };

  return (
    <div style={{width: '100%', display: 'flex', justifyContent: 'center'}}>
      <form onSubmit={handleSubmit} className='w-4/5'>
        <div style={{margin: '10px'}}>
          <input className='input input-bordered w-full max-w-xs'
            placeholder="Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div role="tablist" class="tabs tabs-lifted" style={{margin: '10px'}}>
          <input type="radio" name="preview" role="tab" class="tab" aria-label="Editor" checked/>
          <div role="tabpanel" class="tab-content bg-base-100 border-base-300 rounded-box">
            <textarea
              className="textarea input-bordered w-full p-4"
              placeholder="Contents"
              value={contents}
              onChange={(e) => setContents(e.target.value)}
            />
          </div>

          <input type="radio" name="preview" role="tab" class="tab" aria-label="Preview" />
          <div role="tabpanel" class="tab-content bg-base-100 border-base-300 rounded-box p-6">{contents}</div>
        </div>
        <input type="checkbox" className="btn btn-xs" aria-label="Enable Markdown" checked={markdown} onChange={(e) => setMarkdown(e.target.checked)} style={{margin: '10px'}}/>
        <input className="block w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400" id="multiple_files" type="file" multiple onChange={handleFileChange} style={{margin: '10px'}}/>
        <button className="btn btn-wide" type="submit" style={{margin: '10px'}}>Upload!</button>
      </form>
    </div>
  );
}

export default Workroom;
