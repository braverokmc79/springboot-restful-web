// src/queryClient.ts
import { QueryClient } from '@tanstack/react-query';

export const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      staleTime: 1000 * 60 * 5, // 데이터 신선도 시간 5분           
      cacheTime: 1000 * 60 * 10, // 캐시 유지 시간 10분
      retry: 2, // 실패 시 재시도 횟수
      refetchOnWindowFocus: true, // 창에 포커스가 맞춰질 때마다 데이터 다시 가져오기
    } as const,
  }  ,
});
