import React, { useState, useContext } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../../services/AuthContext';
import showMetadataModal from './metamodal';
import ReactQuill, { Quill } from 'react-quill';
import 'react-quill/dist/quill.snow.css';
// import ImageResize from 'quill-image-resize-module';

// Quill.register('modules/ImageResize', ImageResize)

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
  
    const afiles = [];
    const nonaudio = [];
  
    for (const file of files) {
      const mimeType = file.type; // getMimeType 함수를 구현해야 합니다.
  
      if (mimeType.startsWith('audio/')) {
        // Audio 파일인 경우
        const metadata = await showMetadataModal(file); // showMetadataModal 함수를 구현해야 합니다.
  
        if (metadata) {
          afiles.push({ file, metadata });
        } else {
          nonaudio.push(file);
        }
      } else {
        nonaudio.push(file);
      }
    };
  
    afiles.forEach((item, index) => {
      formData.append(`audio[${index}]`, item.file);
      formData.append(`audio[${index}].title`, item.metadata.title);
      formData.append(`audio[${index}].artist`, item.metadata.artist);
    });
  
    nonaudio.forEach((file, index) => {
      formData.append(`files[${index}]`, file, file.name);
    });
  
    const response = await axios.post('http://localhost:8080/backend/mixtape/create', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      },
      withCredentials: true
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
        <div style={{ margin: '10px' }}>
          <ReactQuill
            theme="snow"
            value={contents}
            onChange={setContents}
            modules={modules}
            formats={formats}
          />
        </div>
        <div><input type="checkbox" className="btn btn-xs" aria-label="Enable Markdown" checked={markdown} onChange={(e) => setMarkdown(e.target.checked)} style={{margin: '10px'}}/></div>
        <div><input className="file-input w-full max-w-xs" id="multiple_files" type="file" multiple onChange={handleFileChange} style={{margin: '10px'}}/></div>
        <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}><button className="btn btn-wide" type="submit" style={{margin: '10px'}}>Upload!</button></div>
      </form>
    </div>
  );
}

const modules = {
  toolbar: [
    ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
    ['blockquote'],
    ['link', 'image', 'video', 'formula'],
  
    [{ 'header': 1 }, { 'header': 2 }],               // custom button values
    [{ 'list': 'ordered'}, { 'list': 'bullet' }],
    [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
    [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
    [{ 'direction': 'rtl' }],                         // text direction
  
    [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
    [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
  
    [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
    [{ 'font': [] }],
    [{ 'align': [] }],
  
    ['clean']                                         // remove formatting button
  ],
  // ImageResize: {
  // 	parchment: Quill.import('parchment')
  // }
};

const formats = [
  'header',
  'bold',
  'italic',
  'underline',
  'strike',
  'blockquote',
  'list',
  'bullet',
  'indent',
  'link',
  'image',
  'video',
  'formula',
  'direction',
  'color',
  'background',
  'font',
  'align',
  'script'
];

export default Workroom;
