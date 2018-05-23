import React from 'react';
import { I18n} from 'react-i18next';
import styles from './SelectionItem.css';

const SelectionItem = ({namespace, icon, link}) => (
  <I18n ns="selection">
    {t => (
      <div className={styles['flex-item']}>
        <div className={styles['title-wrapper']}>
          <div className={styles['image-wrapper']}>
            <img src={icon} alt={t(namespace + '.icon')} />
          </div>
          <h2 className={styles.title}>{t(namespace + '.title')}</h2>
        </div>

        <div className={styles['content-wrapper']}>
          <p className={styles.subtitle}>{t(namespace + '.subtitle')}</p>
          <div className={styles['list-wrapper']}>
            <p>{t(namespace + '.label')}</p>
            <ul className={styles.list}>
              <li>{t(namespace + '.item1')}</li>
              <li>{t(namespace + '.item2')}</li>
              <li>{t(namespace + '.item3')}</li>
            </ul>
          </div>
        </div>
        <div className={styles['btn-holder']}>
          <a className={styles.link} href={link}>{t(namespace + '.link')}</a>
        </div>
      </div>
    )}
  </I18n>
);

export default SelectionItem;
