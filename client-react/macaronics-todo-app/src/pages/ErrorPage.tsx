import PageContent from "@/components/PageContent";
import React from "react";
import { useRouteError } from "react-router-dom";


type ErrorType = {
    status: number;
    data: {
      message: string;
    };
};

const ErrorPage: React.FC = () => {
  
  const error = useRouteError() as ErrorType;


  if ('status' in error && 'data' in error) {
    // error is now known to be of type ErrorType
    console.log("error   : ", error);
    // ...
  }

  let title = "에러 발생됨!!";
  let message = "에러가 발생했습니다.";

  if (error.status === 500) {
    message = error.data.message;
  }

  if (error.status === 404) {
    title = "404 Error";
    message = "페이지를 찾을 수 없습니다.";
  }

  return (
    <>
      <PageContent title={title}>
        <p>{message}</p>
      </PageContent>
    </>
  );

};

export default ErrorPage;
