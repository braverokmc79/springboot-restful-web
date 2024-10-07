import { createBrowserRouter , RouterProvider} from 'react-router-dom';
import './App.css'
import TodoApp from './pages/todo/TodoApp'
import HomePage from './pages/HomePage';
import ErrorPage from './pages/ErrorPage';
import RootLayout from './pages/RootLayout';
import LoginPage from './pages/login/LoginPage';
import TodoLayout from './components/todo/TodoLayout';

import TodoListPage from './pages/todo/TodoListPage';

//라우트 정보를 담는 객체 배열
const router =createBrowserRouter( [
  {
    path: '/',
    element: <RootLayout />,
    errorElement: <ErrorPage/>,
    //loader:,
    id:"root",
    children: [
      { index: true, element: <HomePage /> },
      {
        path: 'todo',      
        element: <TodoLayout />,
        children: [
          { 
            index: true,
          },
          {
            path: ':username',
            element: <TodoApp />
          },
          {
            path: 'list',
            element: <TodoListPage />
          }
                 
        ]
      },
      {
        path: 'login',      
        element: <LoginPage />,
        children: [
          { 
            index: true,
          },
                 
        ]
      }
      ,
      
    ]
  },
]);



function App() {
  return <RouterProvider router={router} />     ;  
}

export default App
