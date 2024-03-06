import Modal from '../../components/Login/Modal';
import PopularHalls from '../../components/PopularHalls';
import logo from '../../logo.svg';
import './assets/studio.css';

const Studio = () => {
  return (
    <div className="App" style={{padding: '10px'}}>
      <PopularHalls/>
      <Modal/>
    </div>
  );
}

export default Studio;
