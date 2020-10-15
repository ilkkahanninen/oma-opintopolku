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
        birthDay: new Date(),
        personOid: "",
        hetu: ""
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
      <Selection isLoggedIn={this.state.isLoggedIn}/>
    </Fragment>
  )}
}

export default Home;
