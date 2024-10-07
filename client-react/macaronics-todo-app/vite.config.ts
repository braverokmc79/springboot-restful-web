import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'
import path from 'path';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),  // @ 경로를 src 폴더로 설정
    },
  },
  server: {
    port: 3000, // 원하는 포트 번호로 변경 (예: 3000)
  },
});