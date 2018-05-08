import React, { Fragment } from 'react';
import Selection from '../selection/Selection';
import styles from './Home.css';

const Home = () => (
  <Fragment className={styles['content-wrapper']}>
    <div id="greetings">
      <h1>Tervetuloa Oma opintopolkuun, Clara Nieminen</h1>
      <p className={styles.subtitle}>
        Oma opintopolussa voit tarkastella omia opintosuorituksia vuosien varrelta,
        tai hakemuksiasi eri oppilaitoksiin.
      </p>
    </div>

    <div id="login">
      <h2>
        Kirjautumista varten sinun tulee tunnistautua pankkitunnuksella,
        mobiilivarmenteella tai sirullisella henkilökortilla.
      </h2>
      <button>Kirjaudu sisään</button>
    </div>

    <Selection />
  </Fragment>
);

export default Home;
