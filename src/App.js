import React, { Fragment } from 'react';
import Selection from './components/selection/Selection';

const App = () => (
  <Fragment>
    <div id="greetings">
      <h1>Tervetuloa Oma opintopolkuun, Clara Nieminen</h1>
      <p>
        Oma opintopolussa voit tarkastella omia opintosuorituksia vuosien varrelta,
        tai hakemuksiasi eri oppilaitoksiin.
      </p>
    </div>

    <div id="login">
      <h3>
        Kirjautumista varten sinun tulee tunnistautua pankkitunnuksella,
        mobiilivarmenteella tai sirullisella henkilökortilla.
      </h3>
      <button>Kirjaudu sisään</button>
    </div>

    <Selection />
  </Fragment>
);

export default App;
