import React from 'react';
import { I18n } from 'react-i18next';
import styles from '../Greeting.css';

const UserGreeting = ({ name }) => (
  <I18n ns="home">
    {t => (
      <div className={styles['greeting-container']}>
        <h1>{t('greeting')}</h1>
        <p className={styles.subtitle}>{t('description')}</p>
        <p className={styles.identity}>{ name }</p>
      </div>
    )}
  </I18n>
);

export default UserGreeting;
