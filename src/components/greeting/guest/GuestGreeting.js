import React from 'react';
import { I18n } from 'react-i18next';
import { login } from '../../../utils.js'
import styles from '../Greeting.css';

const GuestGreeting = () => (
  <I18n ns="home">
    {t => (
      <div className={styles['greeting-container-space-below']}>
        <p className={styles['guest-greeting']}>{t('login.info')}</p>
        <a className={styles['login-button']} onClick={ login } tabIndex="0">{t('common:login')}</a>
      </div>
    )}
  </I18n>
);

export default GuestGreeting;
