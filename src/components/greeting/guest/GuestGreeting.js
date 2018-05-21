import React from 'react';
import { I18n } from 'react-i18next';
import { getUser } from 'Src/utils.js'

const GuestGreeting = () => (
  <I18n ns="home">
    {t => (
      <div id="login">
        <h1>{t('login.info')}</h1>
        <button onClick={ getUser }>{t('common:login')}</button>
      </div>
    )}
  </I18n>
);

export default GuestGreeting;
