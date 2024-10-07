import React, { useState } from 'react';
import styles from './Counter.module.css'; // 모듈 CSS 불러오기

interface CounterProps {
  by: number;
  increateTotCount: (by: number) => void;   
  decrementTotCount: (by: number) => void;
}

const CounterButton: React.FC<CounterProps> = ({ by , increateTotCount, decrementTotCount }) => { // 구조 분해 할당
  // 상태 선언: count 값은 0으로 초기화
  const [count, setCount] = useState(0);

  // 카운터 증가 함수
  const incrementCounterFunction = (e: React.MouseEvent<HTMLButtonElement>) => {
    const cnt= count + by;
    setCount(cnt); // 'by'는 이미 숫자이므로 Number() 필요 없음
    increateTotCount(by);  
};

  // 카운터 감소 함수
  const decrementCounterFunction = (e: React.MouseEvent<HTMLButtonElement>) => {
    const cnt= count - by;
    setCount(cnt);
    decrementTotCount(by);
  };


  return (
    <div className={`w-full flex flex-col justify-center items-center md:flex-row ${styles.counter}`}>
      <button
        type='button'
        className="counterButton mx-3 px-4 py-2 bg-gray-500 text-white rounded text-3xl hover:bg-gray-600 focus:outline-none focus:ring-0 shadow-md"
        onClick={decrementCounterFunction}
      >
        -
      </button>

      <span className={`count text-3xl`}>{count}</span> {/* count 값 표시 */}

      <button
        type='button'
        className="counterButton mx-3 px-4 py-2 bg-gray-500 text-white rounded text-3xl hover:bg-gray-600 focus:outline-none focus:ring-0 shadow-md"
        onClick={incrementCounterFunction}
      >
        +
      </button>
    </div>
  );
};

export default CounterButton;
