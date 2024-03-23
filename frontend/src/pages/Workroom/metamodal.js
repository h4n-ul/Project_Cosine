import React, { useState } from 'react';
import ReactDOM from 'react-dom';

const MetadataModal = ({ file, onSubmit, onClose }) => {
  const [title, setTitle] = useState('');
  const [artist, setArtist] = useState('');

  const handleSubmit = () => {
    onSubmit({ title, artist });
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
            <span className="label-text">Artist</span>
          </label>
          <input
            type="text"
            placeholder="Enter artist"
            className="input input-bordered"
            value={artist}
            onChange={(e) => setArtist(e.target.value)}
          />
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