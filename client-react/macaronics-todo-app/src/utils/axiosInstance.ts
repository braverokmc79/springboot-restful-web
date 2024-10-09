// src/utils/axiosInstance.js

import axios from 'axios';

// axios 인스턴스 생성
const axiosInstance = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL,
  timeout: 10000, // 요청 타임아웃 설정 (ms)
  headers: {
    'Content-Type': 'application/json',
    // 필요 시 인증 토큰이나 다른 헤더를 추가할 수 있습니다.
    // 'Authorization': `Bearer ${token}`
  },
});

// 요청 인터셉터 추가
axiosInstance.interceptors.request.use(
  (config) => {
    // 요청 전 실행할 코드 (ex: 토큰 자동으로 추가)
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    // 요청 에러 처리
    return Promise.reject(error);
  }
);

// 응답 인터셉터 추가
axiosInstance.interceptors.response.use(
  (response) => {
    // 응답 데이터 처리
    return response;
  },
  (error) => {
    // 응답 에러 처리 (ex: 401 에러 처리)
    if (error.response.status === 401) {
      console.error('Unauthorized, redirect to login');
      // 로그아웃 처리 또는 로그인 페이지로 리다이렉트 등
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
