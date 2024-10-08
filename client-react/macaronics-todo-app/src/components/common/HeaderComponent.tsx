import React, { useContext } from "react";
import { Link } from "react-router-dom";
import reactLogo from "@/assets/react.svg";
import {
  Menubar,
  MenubarContent,
  MenubarItem,
  MenubarMenu,  
  MenubarSeparator,
  MenubarShortcut,
  MenubarSub,
  MenubarSubContent,
  MenubarSubTrigger,
  MenubarTrigger,
} from "@/components/ui/menubar";
import { AuthContext } from "../security/AuthContext"; // 변경: AuthContext를 직접 가져옴



export interface AuthContextType {
  number: number;
  // other properties...
}


const HeaderComponent: React.FC = () => {
  const authContext = useContext(AuthContext) as AuthContextType; 

  // authContext가 null일 경우를 처리하는 것이 좋습니다.
  if (!authContext) {
    return null; // 또는 로딩 스피너를 보여줄 수 있습니다.
  }

  console.log(  " 인증=======>" ,authContext.number);

  return (
    <Menubar className="h-16 md:justify-between">
      <div className="flex items-center">
        <MenubarMenu>
          <MenubarTrigger className="cursor-pointer">
            <Link to="/">
              <img src={reactLogo} alt="logo" />
            </Link>          
          </MenubarTrigger>     
        </MenubarMenu>

        <MenubarMenu>
          <MenubarTrigger className="cursor-pointer">
            <Link to="/">홈</Link>          
          </MenubarTrigger>     
        </MenubarMenu>
      
        <MenubarMenu>
          <MenubarTrigger>할일</MenubarTrigger>
          <MenubarContent>
            <MenubarItem>
              <Link to="/todo/list">하일 목록</Link>
            </MenubarItem>
            <MenubarItem>
              Redo <MenubarShortcut>⇧⌘Z</MenubarShortcut>
            </MenubarItem>
            <MenubarSeparator />
            <MenubarSub>
              <MenubarSubTrigger>Find</MenubarSubTrigger>
              <MenubarSubContent>
                <MenubarItem>Search the web</MenubarItem>
                <MenubarSeparator />
                <MenubarItem>Find...</MenubarItem>
                <MenubarItem>Find Next</MenubarItem>
                <MenubarItem>Find Previous</MenubarItem>
              </MenubarSubContent>
            </MenubarSub>
            <MenubarSeparator />
            <MenubarItem>Cut</MenubarItem>
            <MenubarItem>Copy</MenubarItem>
            <MenubarItem>Paste</MenubarItem>
          </MenubarContent>
        </MenubarMenu>            
      </div>

      <div className="flex items-center">
        <MenubarMenu>
          <MenubarTrigger>회원</MenubarTrigger>
          <MenubarContent>
            <MenubarItem>
              <Link to="/login">로그인</Link>
            </MenubarItem>
            <MenubarItem>
              회원가입 <MenubarShortcut>⇧⌘Z</MenubarShortcut>
            </MenubarItem>
            <MenubarSeparator />               
          </MenubarContent>     
        </MenubarMenu>
        <MenubarMenu>
          <MenubarTrigger className="cursor-pointer">
            <Link to="/logout">로그아웃</Link>   
          </MenubarTrigger>     
        </MenubarMenu>
      </div>
    </Menubar>
  );
}

export default HeaderComponent;
