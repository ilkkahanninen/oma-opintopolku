import React from 'react';
import ReactDOM from 'react-dom';
import './main.css';
import './i18n';
import Home from './components/home/Home';
import registerServiceWorker from './registerServiceWorker';


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

function getLang() {
  if (window.Raamit && typeof window.Raamit.getLanguage === 'function') {
    return window.Raamit.getLanguage();
  }
  return "FI";
}

function createLoginUrl(lang) {
  const domain = window.location.origin;
  return domain + '/shibboleth/Login' + lang +'?target=' + domain + '/oma-opintopolku';
}

function createLogoutUrl() {
  const domain = window.location.origin;
  return domain + '/omatsivut/Shibboleth.sso/Logout?return=' + domain + '/oma-opintopolku';
}

window.Service = {
  login() {
    const lang = getLang().toUpperCase();
    window.location.replace(createLoginUrl(lang));
  },
  logout() {
    window.location.replace(createLogoutUrl());
  },
  getUser() {
    return new Promise((resolve, reject) => {
      fetch('/oma-opintopolku/session', {
        credentials: 'same-origin'
      })
      .then((response) => {
        if (response.status === 200) {
          response.json().then((data) => {
            domLoggedIn();
            resolve(data);
          })
        } else {
          domLoggedOut();
          reject(new Error('No session found!'));
        }
      }).error(err => {
        console.error(err);
        reject(new Error('Failed to fetch session!'));
      });
    })
  }
};

ReactDOM.render(<Home name="Erkki Esimerkki" />, document.getElementById('content'));
registerServiceWorker();
