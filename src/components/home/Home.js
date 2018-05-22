import React, { Fragment } from 'react';
import Selection from '../selection/Selection';
import Greeting from '../greeting/Greeting';
import styles from './Home.css';

class Home extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      user: {
        name: "",
        birthDay: new Date()
      },
      isLoggedIn: false
    }
  }

  setUser = (user) => {
    if (user) {
      this.setState({user: user, isLoggedIn: true});
    }
  };

  setLoggedIn = (value) => {
    this.setState({isLoggedIn: value});
  };

  render() {
    return (
    <Fragment>
      <Greeting user={this.state.user} isLoggedIn={this.state.isLoggedIn} />
      <Selection />
    </Fragment>
  )}
}

export default Home;
