export function getUser() {
  return new Promise((resolve, reject) => {
    fetch('/oma-opintopolku/session', {
      credentials: 'same-origin'
    })
      .then((response) => {
        if (response.status === 200) {
          response.json().then((data) => {
            //domLoggedIn(); set isLoggedIn state to true or pass different props to home
            resolve(data);
          })
        } else {
          //domLoggedOut(); set isLoggedIn state to false or pass different props to home
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
  window.location.replace(createLogoutUrl());
}

function createLoginUrl(lang) {
  const domain = window.location.origin;
  return domain + '/shibboleth/Login' + lang +'?target=' + domain + '/oma-opintopolku';
}

function createLogoutUrl() {
  const domain = window.location.origin;
  return domain + '/omatsivut/Shibboleth.sso/Logout?return=' + domain + '/oma-opintopolku';
}

function getLang() {
  if (window.Raamit && typeof window.Raamit.getLanguage === 'function') {
    return window.Raamit.getLanguage();
  }
  return "FI";
}

