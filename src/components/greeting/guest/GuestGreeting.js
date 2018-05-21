import React from 'react';
import { I18n } from 'react-i18next';

const GuestGreeting = () => (
  <I18n ns="home">
    {t => (
      <div id="login">
        <h1>{t('login.info')}</h1>
        <button>{t('common:login')}</button>
      </div>
    )}
  </I18n>
);

export default GuestGreeting;
