import React from 'react';
import PropTypes from 'prop-types';
import styles from './SelectionButton.css';

const SelectionButton = props => (
  <button className={styles.button}>{props.text}</button>
);

SelectionButton.propTypes = {
  text: PropTypes.string,
};

SelectionButton.defaultProps = {
  text: '',
};

export default SelectionButton;

