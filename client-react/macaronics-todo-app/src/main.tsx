import React from 'react'
import ReactDOM from 'react-dom/client'
import './index.css'
import { QueryClientProvider } from '@tanstack/react-query';
import { queryClient } from './utils/queryClient';
import App from './App.tsx'


ReactDOM.createRoot(document.getElementById('root')!).render(
  // <React.StrictMode>
  //   <App />
  // </React.StrictMode>,
  <QueryClientProvider client={queryClient}>
    <App />
  </QueryClientProvider>
)

