import React, { useState } from 'react';
import CounterButton from './CounterButton';
import styles from './Counter.module.css';


const Counter: React.FC = () => { // 구조 분해 할당
  // 상태 선언: count 값은 0으로 초기화
  const [totCount, setTotCount] = useState(0);

  // 카운터 증가 함수
  const increateTotCount = (by: number) => {
    setTotCount(totCount + by); // 'by'는 이미 숫자이므로 Number() 필요 없음
  };

  // 카운터 감소 함수
  const decrementTotCount = (by: number) => {
    setTotCount(totCount - by);
  };

  return (
    <div className={`w-full flex flex-col justify-center items-center  ${styles.counter}`}>
        
        <CounterButton key={1}  by={1} increateTotCount={increateTotCount} decrementTotCount={decrementTotCount} /> 
        
        <CounterButton key={5}  by={5} increateTotCount={increateTotCount} decrementTotCount={decrementTotCount} /> 

        <CounterButton key={10}  by={10} increateTotCount={increateTotCount} decrementTotCount={decrementTotCount} /> 


        <div className='count text-3xl mt-5'>
            {totCount}
        </div>
    </div>
  );
};

export default Counter;
