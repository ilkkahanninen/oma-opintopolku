import React from 'react';
import ReactDOM from 'react-dom';
import './main.css';
import Home from './components/home/Home';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<Home />, document.getElementById('content'));
registerServiceWorker();
