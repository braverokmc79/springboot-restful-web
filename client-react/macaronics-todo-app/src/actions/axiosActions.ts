import axiosInstance from "@/utils/axiosInstance";

export interface ApiResponse<T> {
  apiData: T | null;
  error: Error | null;
}

// GET 요청 함수
export async function getApiData<T>(url: string): Promise<ApiResponse<T>> {
  let apiData: T | null = null;
  let error: Error | null = null;

  try {
     // 3초 지연을 추가
    //await new Promise(resolve => setTimeout(resolve, 3000));

    const response = await axiosInstance.get<T>(url);
    apiData = response.data;
  } catch (err) {
    if (err instanceof Error) {
      error = err;
    }
  }

  return { apiData, error };
}



// POST 요청 함수
export async function postApiData<T, U>(url: string, payload: T): Promise<ApiResponse<U>> {
  let data: U | null = null;
  let error: Error | null = null;

  try {
    const response = await axiosInstance.post<U>(url, payload);
    data = response.data;
  } catch (err) {
    if (err instanceof Error) {
      error = err;
    }
  }

  return { data, error };
}



// PUT 요청 함수
export async function putApiData<T, U>(url: string, payload: T): Promise<ApiResponse<U>> {
  let data: U | null = null;
  let error: Error | null = null;

  try {
    const response = await axiosInstance.put<U>(url, payload);
    data = response.data;
  } catch (err) {
    if (err instanceof Error) {
      error = err;
    }
  }

  return { data, error };
}



// DELETE 요청 함수
export async function deleteApiData<T>(url: string): Promise<ApiResponse<T>> {
  let data: T | null = null;
  let error: Error | null = null;

  try {
    const response = await axiosInstance.delete<T>(url);
    data = response.data;
  } catch (err) {
    if (err instanceof Error) {
      error = err;
    }
  }

  return { data, error };
}
