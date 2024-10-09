import FooterComponent from "@/components/common/FooterComponent";
import HeaderComponent from "@/components/common/HeaderComponent";
import React from "react";
import { Outlet } from "react-router-dom";

const RootLayout: React.FC = () => {
  return (
    <>
      <main className="w-[1580px] mx-auto">
        <HeaderComponent />
        <div className="w-screen md:w-full min-h-screen mb-20">
          <Outlet />
        </div>
        
      </main>
      <FooterComponent />
    </>
  );
};

export default RootLayout;
