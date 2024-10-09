import { getApiData } from '@/actions/axiosActions';
import React, { useEffect, useState } from 'react'

const WelcomeComponent:React.FC = () => {
  const [data, setData] = useState<unknown>(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState<unknown>(null);


  useEffect(() => {
    const fetchData = async () => {
      setLoading(true);
     
      const {apiData, error} =await getApiData("/test/hello")
     
      setData(apiData);
      setError(error);
      setLoading(false);
    };

    fetchData();
  }, []);



  if (loading) return <p className='text-3xl font-bold text-center mt-10'>로딩중...</p>;
  if (error) return <p className='text-3xl font-bold text-center mt-10'>Error occurred: {(error as Error).message}</p>;


    return (
    <div className='Welcome'>          
      <div className='text-3xl font-bold text-center mt-10'>
         Todo Home 환영합니다. - {data}
      </div>
      
    </div>
  )

}

export default WelcomeComponent;