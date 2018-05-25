import React from 'react';
import SelectionItem from '../selectionitem/SelectionItem';
import styles from './Selection.css';
import koskiImg from 'Static/img/opintoni_white.svg';
import omatsivutImg from 'Static/img/hakemukseni_white.svg';

const Selection = ({isLoggedIn}) => (
  <div className={styles['flex-container']}>
    <SelectionItem isLoggedIn={isLoggedIn} namespace='omatsivut' icon={omatsivutImg} link='/omatsivut' />
    <SelectionItem isLoggedIn={isLoggedIn} namespace='koski' icon={koskiImg} link='/koski/omattiedot' />
  </div>
);

export default Selection;
