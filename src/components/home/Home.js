import React, { Fragment } from 'react';
import Selection from '../selection/Selection';
import Greeting from '../greeting/Greeting';
import styles from './Home.css';

class Home extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      user: {
        name: ""
      },
      isLoggedIn: false
    }
  }

  setUser = (user) => {
    this.setState({user: user});
  };

  setLoggedIn = (value) => {
    this.setState({isLoggedIn: value});
  };

  render() {
    return (
    <Fragment>
      <Greeting name={this.state.user.name} isLoggedIn={this.state.isLoggedIn} />
      <Selection />
    </Fragment>
  )}
}

export default Home;
