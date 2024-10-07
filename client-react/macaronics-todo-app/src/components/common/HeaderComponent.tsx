import React from "react";
import {
  NavigationMenu,
  NavigationMenuContent,
  NavigationMenuItem,
  NavigationMenuLink,
  NavigationMenuList,
  NavigationMenuTrigger,
} from "@/components/ui/navigation-menu";
import { Link } from "react-router-dom";
import { navigationMenuTriggerStyle } from "@/components/ui/navigation-menu";

const HeaderComponent: React.FC = () => {
  return (
    <div>
      <NavigationMenu>
        <NavigationMenuList>

         <NavigationMenuItem>
            <Link to="/">
              <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                 홈
              </NavigationMenuLink>
            </Link>
          </NavigationMenuItem>

          <NavigationMenuItem>
            <NavigationMenuTrigger>Item One</NavigationMenuTrigger>
            <NavigationMenuContent>
              <NavigationMenuLink>링크</NavigationMenuLink>
            </NavigationMenuContent>
          </NavigationMenuItem>

          


        </NavigationMenuList>
      </NavigationMenu>
    </div>
  );
};

export default HeaderComponent;
