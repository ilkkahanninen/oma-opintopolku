import React from 'react';
import { I18n } from 'react-i18next';
import styles from './UserGreeting.css';

const UserGreeting = ({ name }) => (
  <I18n ns="home">
    {t => (
      <div>
        <h1>{t('greeting')}</h1>
        <p className={styles.subtitle}>{t('description')}</p>
        <p className={styles.identity}>{ name }</p>
      </div>
    )}
  </I18n>
);

export default UserGreeting;
