import React from 'react';
import ReactDOM from 'react-dom';
import './main.css';
import './i18n';
import Home from './components/home/Home';
import registerServiceWorker from './registerServiceWorker';
import { getUser, login, logout } from './utils';

window.Service = {
  login,
  logout,
  getUser
};

ReactDOM.render(<Home ref={(home) => {window.home = home}} />, document.getElementById('content'));
registerServiceWorker();
