import React, { useState, useContext } from 'react';
import { Navigate, redirect, useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import { AuthContext } from '../../services/AuthContext';
import showMetadataModal from './metamodal';
import ReactQuill, { Quill } from 'react-quill';
import 'react-quill/dist/quill.snow.css';
// import ImageResize from 'quill-image-resize-module';

// Quill.register('modules/imageResize', ImageResize)

const Workroom = () => {
  const { hall } = useParams()
  const { isLoggedIn } = useContext(AuthContext);
  const [title, setTitle] = useState('');
  const [contents, setContents] = useState('');
  const [files, setFiles] = useState([]);
  const navigate = useNavigate();

  if (!isLoggedIn) {
    return <Navigate to={`/b/${hall}`} />;
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('title', title);
    formData.append('contents', contents);
    formData.append('hallId', hall);
  
    const afiles = [];
    const nonaudio = [];
  
    for (const file of files) {
      const mimeType = file.type;
  
      if (mimeType.startsWith('audio/')) {
        const metadata = await showMetadataModal(file);
  
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
      formData.append(`audio[${index}].file`, item.file);
      formData.append(`audio[${index}].title`, item.metadata.title);
      formData.append(`audio[${index}].artist`, item.metadata.artist);
    });
  
    nonaudio.forEach((file, index) => {
      formData.append(`files[${index}]`, file, file.name);
    });
  
    const response = await axios.post('http://localhost:8080/backend/reel/record', formData, {
      header: {
        'Content-Type': 'multipart/form-data'
      },
      withCredentials: true
    });
    if (response.status === 200) {
      console.log(response)
      navigate(`/b/${hall}/${response.data.link}`);
    }
  };

  const handleFileChange = (e) => {
    setFiles(Array.from(e.target.files));
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
            style={{ height: '500px', marginBottom: '50px' }}
          />
        </div>
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
