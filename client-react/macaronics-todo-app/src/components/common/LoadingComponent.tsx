// Spinner.tsx
import React from 'react';
import { SyncLoader } from "react-spinners";



const LoadingSpinner: React.FC = () => {
    return (
    <div className="flex flex-col justify-center items-center mt-10">       
      <SyncLoader />        
       <p className="text-3xl font-bold text-center ml-4 mt-10">로딩중...</p>
    </div>    
    );
};

export default LoadingSpinner;
