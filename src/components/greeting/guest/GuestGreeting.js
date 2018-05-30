import React from 'react';
import { I18n } from 'react-i18next';
import { login } from 'Src/utils.js'
import styles from '../Greeting.css';

const GuestGreeting = () => (
  <I18n ns="home">
    {t => (
      <div className={styles['greeting-container-space-below']}>
        <p className={styles['guest-greeting']}>{t('login.info')}</p>
        <button className={styles['login-button']} onClick={ login }>{t('common:login')}</button>
      </div>
    )}
  </I18n>
);

export default GuestGreeting;
