import axios from 'axios';
import React, { useState } from 'react';

function Signin(props) {
    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: ""
    });

    const handleInputChange = (e) => {
        setSigninInput({
            ...signinInput,
            [e.target.name]: e.target.value
        })
    }

    const handleSigninClick = async () => {
        // async + awit = try / catch 무조건 줄것
        try {
            const response = await axios.post("http://localhost:8080/servlet_study/auth/signin", signinInput);

            console.log(response);

            if(!!!response.data.token) {
                alert("로그인 실패")
                return
            }
            
            localStorage.setItem("token", response.data?.token);
            
            alert("환영합니다.")

        } catch(error) {
            console.log(error)
        }
    }

    return (
        <>
            <h1>로그인</h1>
            <div><input type="text" name="username" onChange={handleInputChange} placeholder='username'/></div>
            <div><input type="password" name="password" onChange={handleInputChange} placeholder='password'/></div>
            <div><button onClick={handleSigninClick}>로그인</button></div>
        </>
    );
}

export default Signin;