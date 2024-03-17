import React, { useEffect, useRef, useState } from 'react';
import '../main.css'
import axios from 'axios';
import videojs from 'video.js';

function AudioPlayer({streamId}) {
    const audioRef = useRef(null);
    const [player, setPlayer] = useState(null);
    const [playing, setPlaying] = useState(false);
    const [progress, setProgress] = useState(0);

    useEffect(() => {
        let isMounted = true;
        const vjsPlayer = videojs(audioRef.current, { controls: true });
    
        vjsPlayer.on('timeupdate', () => {
            setProgress(vjsPlayer.currentTime() / vjsPlayer.duration());
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
                console.log(audioBlob)
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
        <div style={{display: 'flex', alignItems: 'center', margin: '10px'}}>
            <div data-vjs-player>
                <audio ref={audioRef} className="video-js"></audio>
            </div>
            <button className='btn' onClick={togglePlay}>{playing ? 'Pause' : 'Play'}</button>
            <input type="range" min={0} max="1" step="any" className="range range-sm w-80" value={progress} onChange={handleSliderChange} 
            style={{marginLeft: '10px'}}/>
        </div>
    );
}

export default AudioPlayer;
