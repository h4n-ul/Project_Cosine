import React, { useEffect, useRef, useState } from 'react';
import '../main.css'
import axios from 'axios';
import videojs from 'video.js';

const formatTime = (time) => {
  const minutes = Math.floor(time / 60);
  const seconds = Math.floor(time % 60);
  const milliseconds = Math.floor((time % 1) * 1000);

  return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}.${milliseconds.toString().padStart(3, '0')}`;
}

function AudioPlayer({streamId, title, artist}) {
    const audioRef = useRef(null);
    const [player, setPlayer] = useState(null);
    const [playing, setPlaying] = useState(false);
    const [progress, setProgress] = useState(0);

    useEffect(() => {
        let isMounted = true;
        const vjsPlayer = videojs(audioRef.current, { controls: true });
        let rafId;

        const updateTime = () => {
          setProgress(audioRef.current.currentTime / audioRef.current.duration);
          rafId = requestAnimationFrame(updateTime);
        };
      
        audioRef.current.addEventListener('play', () => {
          rafId = requestAnimationFrame(updateTime);
        });
      
        audioRef.current.addEventListener('pause', () => {
          cancelAnimationFrame(rafId);
        });

        setPlayer(vjsPlayer);

        axios({
            url: `http://localhost:8080/backend/stream/${streamId}`,
            method: 'GET',
            responseType: 'blob',
        }).then(response => {
            if (isMounted) {
                const mediaType = response.headers['content-type'];
                const audioBlob = new Blob([response.data], { type: mediaType });
                const audioUrl = URL.createObjectURL(audioBlob);
                vjsPlayer.src({ type: mediaType, src: audioUrl });
            }
        });

        return () => {
            isMounted = false; 
            vjsPlayer.dispose();
        };
    }, [streamId]);


    const togglePlay = () => {
        if (player) {
            if (playing) {
                let playPromise = player.play();

                if (playPromise !== undefined) {
                    playPromise.then(_ => {
                        player.pause();
                    })
                    .catch(error => {
                        console.log(error);
                    });
                }
            } else {
                player.play();
            }

            setPlaying(!playing);
        }
    }

    const handleSliderChange = (e) => {
        const newProgress = e.target.value;
        setProgress(newProgress);
        player.currentTime(newProgress * player.duration());
    }

    return (
        <div style={{margin: '10px'}}>
            <div data-vjs-player style={{display: 'none'}}>
                <audio ref={audioRef} className="video-js"></audio>
            </div>
            <p style={{fontWeight: '400', fontSize: '12px', marginBottom: '10px'}}>{title} - {artist.join(', ')}</p>
            {audioRef == null ? <p style={{fontWeight: '400', fontSize: '12px', marginBottom: '10px'}}>{formatTime(audioRef.current.currentTime)} / {formatTime(audioRef.current.duration)}</p> : null}
            <div style={{display: 'flex', alignItems: 'center'}}>
                <button className='btn' onClick={togglePlay}>{playing ? 'Pause' : 'Play'}</button>
                <input type="range" min={0} max="1" step="any" className="range range-sm w-80" value={progress} onChange={handleSliderChange} 
                style={{marginLeft: '10px'}}/>
            </div>
        </div>
    );
}

export default AudioPlayer;
