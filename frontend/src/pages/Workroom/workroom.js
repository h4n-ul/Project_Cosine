import React, { useState } from 'react';
import axios from 'axios';

const Workroom = () => {
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
    <form onSubmit={handleSubmit} className='w-4/5'>
      <div className='input input-bordered w-full max-w-xs'>
        <input 
          placeholder="Title"
          style={{padding: '12px'}}
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
      </div>
      <div style={{margin: '10px'}}>
        <textarea
          className="textarea w-full"
          style={{padding: '20px'}}
          placeholder="Contents"
          value={contents}
          onChange={(e) => setContents(e.target.value)}
        />
      </div>
      <input type="checkbox" className="btn" aria-label="Enable Markdown" checked={markdown} onChange={(e) => setMarkdown(e.target.checked)} />
      <input class="block w-full text-sm text-gray-900 border border-gray-300 rounded-lg cursor-pointer bg-gray-50 dark:text-gray-400 focus:outline-none dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400" id="multiple_files" type="file" multiple onChange={handleFileChange} />
      <button className="btn btn-wide" type="submit">Upload!</button>
    </form>
  );
}

export default Workroom;
