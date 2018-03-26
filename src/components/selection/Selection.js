import React from 'react';
import SelectionButton from '../common/selectionbutton/SelectionButton';
import styles from './Selection.css';

const Selection = () => (
  <div className={styles['flex-container-row']}>
    <div className={styles['flex-item']}>
      <h3>Hakemukseni</h3>
      <ul className={styles.list}>
        <li>Oppilaitoksiin hakeminen</li>
        <li>Hakutuloksien tarkastelu</li>
        <li>Opiskelupaikan vastaanottaminen</li>
      </ul>
      <div className={styles['btn-holder']}>
        <SelectionButton text="Siirry hakemuksiin" />
      </div>
    </div>

    <div className={styles['flex-item']}>
      <h3>Opintoni</h3>
      <p>Tarkastele ja jaa opintotietojasi</p>
      <br />
      <p>Löytyvät opintotiedot:</p>
      <ul className={styles.list}>
        <li>Peruskoulusta, lukiosta ja ammattikoulusta alkaen vuodelta 2018</li>
        <li>Ylioppilastutkinnot alkaen vuodelta 1990</li>
        <li>Korkeakoulusta alkaen vuodelta 1995 (tässä voi olla koulukohtaisia poikkeuksia)</li>
      </ul>
      <div className={styles['btn-holder']}>
        <SelectionButton text="Siirry opintoihin" />
      </div>
    </div>
  </div>
);

export default Selection;
