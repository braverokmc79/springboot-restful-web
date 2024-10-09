import React from "react";
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
import { AuthContextType } from "../security/AuthContext";
import { useAuth } from "../security/UseAuth";



const HeaderComponent: React.FC = () => {
  const authContext = useAuth()  as AuthContextType; // useAuth 훅을 사용하여 number 값 가져오기
  //console.log(  " 인증=======>" ,authContext);


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
        
        {authContext && !authContext.isAuthenticated  &&
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
        }

          {authContext && authContext.isAuthenticated  &&
                  <MenubarMenu>
                    <MenubarTrigger className="cursor-pointer">
                      <Link to="/logout">로그아웃</Link>   
                    </MenubarTrigger>     
                  </MenubarMenu>

          }
      </div>
    </Menubar>
  );
}

export default HeaderComponent;
