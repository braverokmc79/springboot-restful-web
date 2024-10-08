import FooterComponent from "@/components/common/FooterComponent";
import HeaderComponent from "@/components/common/HeaderComponent";
import React from "react";
import { Outlet } from "react-router-dom";

const RootLayout: React.FC = () => {
  return (
    <>
      <main className="w-[1580px] mx-auto">
        <HeaderComponent />
        <Outlet />
      </main>
      <FooterComponent />
    </>
  );
};

export default RootLayout;
