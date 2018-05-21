import React from 'react';
import SelectionItem from '../selectionitem/SelectionItem';
import styles from './Selection.css';
import koskiImg from 'Static/img/opintoni.svg';
import omatsivutImg from 'Static/img/hakemukseni.svg';

const Selection = () => (
  <div className={styles['flex-container']}>
    <SelectionItem namespace='omatsivut' icon={omatsivutImg} />
    <SelectionItem namespace='koski' icon={koskiImg} />
  </div>
);

export default Selection;
