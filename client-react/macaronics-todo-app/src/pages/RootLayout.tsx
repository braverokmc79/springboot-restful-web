import React from "react";
import { Outlet } from "react-router-dom";

const RootLayout: React.FC = () => {
  return (
    <>
      <main className='w-[1580px] mx-auto'>
        <Outlet />
      </main>
    </>
  );
};

export default RootLayout;
