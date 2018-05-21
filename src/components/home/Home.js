import React, { Fragment } from 'react';
import Selection from '../selection/Selection';
import Greeting from '../greeting/Greeting';
import styles from './Home.css';

class Home extends React.Component {

  constructor(props) {
    super(props);
    this.state = {

    }
  }

  render() {
    return (
    <Fragment>
      <Greeting name={name} isLoggedIn={false} />
      <Selection />
    </Fragment>
  )}
}

export default Home;
