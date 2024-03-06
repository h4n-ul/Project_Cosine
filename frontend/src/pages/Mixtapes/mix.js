import React from 'react';
import ReactMarkdown from 'react-markdown';
import { useParams } from 'react-router-dom';
import remarkGfm from 'remark-gfm'

const Mixtape = () => {
  const { hall, mix } = useParams()

  const title = 'abc'
  const contents = 'xyz'
  const markdown = false
  return (
    <div>
      <div style={{display: 'flex', flexDirection: 'row', justifyContent: 'center', alignItems: 'center'}}>
        <h1 style={{padding: '20px'}}>{title}</h1>
        <p style={{padding: '20px'}}>{hall}/{mix}</p>
      </div>
      <div style={{}}>
        {markdown
          ?<ReactMarkdown remarkPlugins={[remarkGfm]}>{contents}</ReactMarkdown>
          :<div dangerouslySetInnerHTML={{__html: contents}}/>
        }
      </div>
    </div>
  );
}

export default Mixtape;
