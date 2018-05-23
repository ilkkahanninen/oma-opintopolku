import Cookies from 'js-cookie';

const domains = {
  FI: 'https://opintopolku.fi',
  SV: 'https://studieinfo.fi',
  EN: 'https://studyinfo.fi'
};

export function getUser() {
  return new Promise((resolve, reject) => {
    fetch('/oma-opintopolku/session', {
      credentials: 'same-origin'
    })
      .then((response) => {
        if (response.status === 200) {
          response.json().then((user) => {
            window.home.setUser(user);
            resolve(user);
          })
        } else {
          window.home.setLoggedIn(false);
          reject(new Error('No session found!'));
        }
      }).catch(err => {
      console.error(err);
      reject(new Error('Failed to fetch session!'));
    });
  });
}

export function login() {
  const lang = getLang().toUpperCase();
  window.location.replace(createLoginUrl(lang));
}

export function logout() {
  const lang = getLang().toUpperCase();
  window.location.replace(createLogoutUrl(lang));
}

function createLoginUrl(lang) {
  let domain = domains[lang];
  return domain + '/shibboleth/Login' + lang +'?target=' + domain + '/oma-opintopolku';
}

function createLogoutUrl(lang) {
  let domain = domains[lang];
  return domain + '/shibboleth/Logout?return=' + domain + '/oma-opintopolku';
}

function getLang() {
  let lang = Cookies.get('lang');
  if (lang) {
    return lang;
  }

  return getLanguageFromHost();
}

function getLanguageFromHost(host) {
  if (!host) { host = document.location.host; }

  let parts = host.split('.');
  if (parts.length < 2) {
    return 'fi';
  }

  let domain = parts[parts.length - 2];
  if (domain.indexOf('opintopolku') > -1) {
    return 'fi';
  } else if (domain.indexOf('studieinfo') > -1) {
    return 'sv';
  } else if (domain.indexOf('studyinfo') > -1) {
    return 'en'
  }
  return 'fi'
}
