import React from 'react';
import { I18n} from 'react-i18next';
import styles from './Link.css';
import lukkoImg from 'Static/img/lukko.svg';

const Link = ({isLoggedIn, namespace, icon, link}) => (
  <I18n ns="selection">
    {t => (
      <a className={`${styles.link} ${isLoggedIn ? styles['link-loggedin'] : styles['link-loggedout']}`} href={link}>
        <img className={styles['link-image']} src={isLoggedIn ? icon : lukkoImg}/>
        <span className={styles['link-text']}>{t(namespace + '.title')}</span>
      </a>
    )}
  </I18n>
);

export default Link;
