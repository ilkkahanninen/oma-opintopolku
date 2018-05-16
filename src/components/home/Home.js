import React, { Fragment } from 'react';
import { I18n, Trans } from 'react-i18next';
import Selection from '../selection/Selection';
import styles from './Home.css';

const Home = ({ name }) => (
  <I18n ns="home">
    {t => (
      <Fragment>
        <div id="greetings" style={{ display: 'none' }}>
          <Trans i18nKey='greeting' name={name}>
            <h1><span>{name}</span></h1>
          </Trans>
          <p className={styles.subtitle}>{t('description')}</p>
        </div>
        <div id="login">
          <h2>{t('login.info')}</h2>
          <button>{t('common:login')}</button>
        </div>
        <Selection />
      </Fragment>
    )}
  </I18n>
);

export default Home;
