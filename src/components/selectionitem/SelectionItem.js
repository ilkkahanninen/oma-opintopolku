import React from 'react';
import { I18n} from 'react-i18next';
import styles from './SelectionItem.css';
import lukkoImg from 'Static/img/lukko_white.svg';

const SelectionItem = ({isLoggedIn, namespace, icon, link}) => (
  <I18n ns="selection">
    {t => (
      <div className={styles['flex-item']}>
        <div className={styles['title-wrapper']}>
          <div className={styles['image-wrapper']}>
            <img className={styles['icon-image']} src={icon} alt={t(namespace + '.icon')} />
          </div>
          <h2 className={styles.title}>{t(namespace + '.title')}</h2>
        </div>

        <div className={styles['content-wrapper']}>
          <p className={styles.subtitle}>
            {t(namespace + '.subtitle')}
            &nbsp;
            {t(namespace + '.subtitleLink') !== namespace + '.subtitleLink'
              ? <a href={t(namespace + '.subtitleLink.href')} target="_blank">{t(namespace + '.subtitleLink.text')}</a>
              : null}
          </p>
          <div className={styles['list-wrapper']}>
            <p>{t(namespace + '.label')}</p>
            <ul className={styles.list}>
              <li>{t(namespace + '.item1')}</li>
              <li>{t(namespace + '.item2')}</li>
              <li>{t(namespace + '.item3')}</li>
            </ul>
          </div>
        </div>
        <div className={styles['link-container']}>
          <a className={`${styles.link} ${isLoggedIn ? styles['link-loggedin'] : styles['link-loggedout']}`} href={link}>
            { !isLoggedIn ? <img className={styles['link-image']} src={lukkoImg}></img> : null}
            <span className={styles['link-text']}>{t(namespace + '.link')}</span>
          </a>
        </div>
      </div>
    )}
  </I18n>
);

export default SelectionItem;
