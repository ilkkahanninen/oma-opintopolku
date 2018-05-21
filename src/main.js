import React from 'react';
import ReactDOM from 'react-dom';
import './main.css';
import './i18n';
import Home from './components/home/Home';
import registerServiceWorker from './registerServiceWorker';
import { getUser, login, logout } from './utils';


/*
function domLoggedIn() {
  let greetings = document.getElementById('greetings');
  greetings.style.display = 'block';

  let login = document.getElementById('login');
  login.style.display = 'none';
}

function domLoggedOut() {
  let greetings = document.getElementById('greetings');
  greetings.style.display = 'none';

  let login = document.getElementById('login');
  login.style.display = 'block';
}
*/

window.Service = {
  login,
  logout,
  getUser
};

ReactDOM.render(<Home />, document.getElementById('content'));
registerServiceWorker();
