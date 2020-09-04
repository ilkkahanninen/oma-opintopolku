import React from 'react';
import { I18n } from 'react-i18next';
import { login } from '../../../utils.js'
import styles from '../Greeting.css';

const handleLoginClick = e => {
  e.preventDefault()
  login()
}

const GuestGreeting = () => (
  <I18n ns="home">
    {t => (
      <div className={styles['greeting-container-space-below']}>
        <h1 className={styles['guest-header']}>{t('login.header')}</h1>
        <p className={styles['guest-greeting']}>{t('login.info')}</p>
        <a href="" className={styles['login-button']} onClick={ handleLoginClick } tabIndex="0">{t('common:login')}</a>
      </div>
    )}
  </I18n>
);

export default GuestGreeting;
