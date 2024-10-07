import React, { useState } from 'react';
import { useNavigate} from 'react-router-dom';

const LoginComponent: React.FC = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const [showSuccess, setShowSuccess] = useState(false);
  const [showError, setShowError] = useState(false);

  const navigator =useNavigate();

  function handleSubmit() {
    console.log("fddd");
    if (!username || !password) {
      setShowError(true);
      setShowSuccess(false);
      return; // Early exit if fields are empty
    }

    if (username === 'test' && password === '1111') {
      console.log('로그인 성공');
      setShowSuccess(true);
      setShowError(false);
      navigator("/todo/"+username);

    } else {
      console.log("로그인 실패");
      setShowSuccess(false);
      setShowError(true);
    }
  }

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 py-20">
      <div className="w-full max-w-2xl p-8 bg-white rounded-lg shadow-lg">
        <h2 className="text-2xl font-bold text-center text-gray-800 mb-6">로그인</h2>


        {/* Success Message */}
        {showSuccess && (
          <div className="text-green-600 font-bold my-5">
            로그인 성공
          </div>
        )}

        {/* Error Message */}
        {showError && (
          <div className="text-red-600 font-bold my-5">
            로그인 실패
          </div>
        )}

             
        <div className="mb-4 flex flex-row items-center justify-center">
          <label className="w-3/12 block text-sm font-medium text-gray-700 mb-2">아이디</label>
          <input
            type="text"
            name="username"
            className="w-9/12 px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            onChange={(e) => setUsername(e.target.value)}
            value={username}
            placeholder="아이디"
          />
        </div>

        <div className="mb-6 flex flex-row">
          <label className="w-3/12 block text-sm font-medium text-gray-700 mb-2">비번</label>
          <input
            type="password"
            name="password"
            className="w-9/12 px-4 py-2 border border-gray-300 rounded-md shadow-sm focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
            onChange={(e) => setPassword(e.target.value)}
            value={password}
            placeholder="비밀번호"
          />
        </div>

        <div className="flex justify-center">
          <button
            type="button"
            name="login"
            className="w-full py-2 px-4 bg-blue-500 text-white font-semibold rounded-md hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:ring-offset-2"
            onClick={handleSubmit}
          >
            로그인
          </button>
        </div>
      </div>
    </div>
  );


};

export default LoginComponent;
