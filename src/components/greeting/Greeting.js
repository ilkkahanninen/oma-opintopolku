import React from 'react';
import UserGreeting from './user/UserGreeting';
import GuestGreeting from './guest/GuestGreeting';

const Greeting = ({name, isLoggedIn}) => {
  if (isLoggedIn) {
    return <UserGreeting name={name} />
  }
  return <GuestGreeting />
};

export default Greeting;
