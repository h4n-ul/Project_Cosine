import React, { useState } from 'react';
import ReactDOM from 'react-dom';

const MetadataModal = ({ file, onSubmit, onClose }) => {
  const [title, setTitle] = useState('');
  const [artists, setArtists] = useState([]);
  const [isOriginal, setIsOriginal] = useState(false);

  const handleSubmit = () => {
    onSubmit({ title, artists, isOriginal });
  };

  const handleArtistChange = (e, index) => {
    const newArtists = [...artists];
    newArtists[index] = e.target.value;
    setArtists(newArtists);
  };

  const addArtist = () => {
    setArtists([...artists, '']);
  };

  const removeArtist = (index) => {
    setArtists([...artists].splice(index, 1));
  };

  return ReactDOM.createPortal(
    <div className="modal modal-open">
      <div className="modal-box">
        <button className="btn btn-sm btn-circle btn-ghost absolute right-2 top-2" onClick={onClose}>
          ✕
        </button>
        <h3 className="font-bold text-lg">{file.name}</h3>
        <div className="form-control">
          <label className="label">
            <span className="label-text">Title</span>
          </label>
          <input
            type="text"
            placeholder="Enter title"
            className="input input-bordered"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <div className="form-control">
          <label className="label">
            <span className="label-text">Artists</span>
          </label>
          {artists.map((artist, index) => (
            <div key={index} className="flex items-center mb-2">
              <input
                type="text"
                placeholder="Enter artist"
                className="input input-bordered mr-2"
                value={artist}
                onChange={(e) => handleArtistChange(e, index)}
              />
              <button className="btn btn-sm btn-circle btn-ghost" onClick={() => removeArtist(index)}>
                ✕
              </button>
            </div>
          ))}
          <button className="btn btn-sm btn-neutral" onClick={addArtist}>
            Add Artist
          </button>
        </div>
        <div className="form-control">
          <label className="label cursor-pointer">
            <span className="label-text" style={{margin: '10px'}}>Original</span>
            <input
              type="checkbox"
              className="checkbox"
              checked={isOriginal}
              onChange={(e) => setIsOriginal(e.target.checked)}
            />
          </label>
        </div>
        <div className="modal-action">
          <button className="btn btn-neutral" onClick={handleSubmit}>
            Submit
          </button>
        </div>
      </div>
    </div>,
    document.body
  );
};

const showMetadataModal = async (file) => {
  return new Promise((resolve) => {
    const modalRoot = document.createElement('div');
    document.body.appendChild(modalRoot);

    const handleClose = () => {
      ReactDOM.unmountComponentAtNode(modalRoot);
      document.body.removeChild(modalRoot);
      resolve(null);
    };

    const handleSubmit = (metadata) => {
      ReactDOM.unmountComponentAtNode(modalRoot);
      document.body.removeChild(modalRoot);
      resolve(metadata);
    };

    ReactDOM.render(
      <MetadataModal
        file={file}
        onClose={handleClose}
        onSubmit={handleSubmit}
      />,
      modalRoot
    );
  });
};

export default showMetadataModal;