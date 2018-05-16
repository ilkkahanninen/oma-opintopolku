import React from 'react';
import { I18n} from 'react-i18next';
import SelectionButton from '../common/selectionbutton/SelectionButton';
import styles from './Selection.css';

const Selection = () => (
  <I18n ns="selection">
    {t => (
      <div className={styles['flex-container']}>
        <div className={styles['flex-item']}>
          <h3>{t('omatsivut.title')}</h3>
          <ul className={styles.list}>
            <li>{t('omatsivut.item1')}</li>
            <li>{t('omatsivut.item2')}</li>
          </ul>
          <div className={styles['btn-holder']}>
            <SelectionButton text={t('omatsivut.button')} />
          </div>
        </div>

        <div className={styles['flex-item']}>
          <h3>{t('koski.title')}</h3>
          <p>{t('koski.note')}</p>
          <br />
          <p className={styles.subtitle}>{t('koski.listLabel')}</p>
          <ul className={styles.list}>
            <li>{t('koski.item1')}</li>
            <li>{t('koski.item2')}</li>
            <li>{t('koski.item3')}</li>
          </ul>
          <div className={styles['btn-holder']}>
            <SelectionButton text={t('koski.button')} />
          </div>
        </div>
      </div>
    )}
  </I18n>
);

export default Selection;
