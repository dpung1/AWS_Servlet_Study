import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const SInputLayout = css`
    margin-bottom: 15px;
    width: 60%;
    height: 40px;

    & > input {
        width: 100%;
        height: 100%;
        text-align: center;
    }
`;

function Signup(props) {
    const navigate = useNavigate(); // 경로이동

    const [ signupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    const handleInputChange = async (e) => {
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        })
    }

    const handleSubmitClick = () => {
        // 회원가입 요청
        const option = {
            params: {
                username: signupUser.username
            }
        }

        const signup = async () => {
            // await 비동기 처리 안에서 비동기 처리를 동기 처리로 변경
            let response = await axios.get("http://localhost:8080/servlet_study/auth/signup/duplicate/username", option)
            
            if(response.data) {
                alert("중복된 아이디 입니다,")
                return
            }
            
            try {
                response = await axios.post("http://localhost:8080/servlet_study/auth/signup", signupUser)
                if(!response.data) {
                    throw new Error(response);
                }
                alert("회원가입 성공!")
                navigate("/signin")
            }catch(error) {
                console.log(error)
            }
        };

        signup();
    };

    return (
        <>
            <h1>회원가입</h1>
            <div css={SInputLayout}>
                <input type="text" name="username" placeholder='username' onChange={handleInputChange}/>
            </div>
            <div css={SInputLayout}>
                <input type="password" name="password" placeholder='password' onChange={handleInputChange}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name="name" placeholder='name' onChange={handleInputChange}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name="email" placeholder='email' onChange={handleInputChange}/>
            </div>
            <div>
                <button onClick={handleSubmitClick}>가입하기</button>
            </div>
        </>
    );
}

export default Signup;