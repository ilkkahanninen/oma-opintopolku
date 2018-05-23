import React from 'react';
import SelectionItem from '../selectionitem/SelectionItem';
import styles from './Selection.css';
import koskiImg from 'Static/img/opintoni_white.svg';
import omatsivutImg from 'Static/img/hakemukseni_white.svg';

const Selection = () => (
  <div className={styles['flex-container']}>
    <SelectionItem namespace='omatsivut' icon={omatsivutImg} link='/omatsivut/login' />
    <SelectionItem namespace='koski' icon={koskiImg} link='/koski/omattiedot' />
  </div>
);

export default Selection;
