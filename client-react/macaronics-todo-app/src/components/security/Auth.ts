import { redirect } from 'react-router-dom';

// 토큰 유효기간을 반환하는 함수
export function getTokenDuration() {
  const storedExpirationDate = localStorage.getItem('expiration') ;
  if (storedExpirationDate === null) {
    return null;
  }
  const expirationDate = new Date(storedExpirationDate);
  const now = new Date();
  const duration = expirationDate.getTime() - now.getTime();
  return duration;
}

// 인증 토큰을 가져오는 함수
export function getAuthToken() {
  const token = localStorage.getItem('token');
  if (!token) {
    return null;
  }

  const tokenDuration = getTokenDuration();
  if (tokenDuration === null) {
    return null;
  }

  if (tokenDuration < 0) {
    return 'EXPIRED';
  }

  return token;
}

// 사용자 정보를 가져오는 함수
export function getUser() {
  const user = localStorage.getItem('user');
  if (user === null) {
    return null;
  }
  const parseUser = JSON.parse(user);
  return parseUser;
}

// 토큰 로더 함수
export async function tokenLoader() {
  return {
    token: getAuthToken(),
    user: getUser(),
  };
}

// 인증 체크 로더 함수
export function checkAuthLoader() {
  const token = getAuthToken();
  if (!token) {
    return redirect('/auth');
  }
  return null;
}

export function isAuthenticatedCheck() {
    //console.log("로컬스토리지 체크  isAuthenticatedCheck: ");
    const isAuthenticated = localStorage.getItem('isAuthenticated');  
    if (!isAuthenticated) {
      return redirect('/login');
    }
    return null;
}