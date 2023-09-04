package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import com.google.gson.Gson;

import data.UserData;
import entity.User;
import lombok.Builder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 회원가입 -> 사용자 정보 데이터의 추가를 의미 
	 * 추가 -> Create, 데이터 베이스에 정보를 insert -> POST 요청
	 * POST 메소드의 특징
	 * 1. 데이터가 주소창에 표시되지 않는다. 
	 * 	-> GET : http://locarhost:8080/category?categoryName=한식(x)
	 *	-> POST : http://locarhost:8080/category (BODY에 데이터를 담아서 서버로 전송)
	 * 2. 전송 데이터의 크기 제한이 없다.
	*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		InputStream inputStream = request.getInputStream();
//		BufferedReader bufferedReader =
//				new BufferedReader(new InputStreamReader(inputStream));
//		
//		StringBuilder requestBody = new StringBuilder("");
//		
//		while(true) {
//			String data = bufferedReader.readLine();
//			if (data == null) {
//				break;
//			}
//			requestBody.append(data);
//		}
//		
//		System.out.println(requestBody.toString());
//		
//		Gson gson = new Gson();
		
//		Map<String, String> useraMap = gson.fromJson(requestBody.toString(), Map.class);
		
// 여기까지 코드 JsonParseUtil Class 안에 있는 코드와 동일
		
		Map<String, Object> userMap = JsonParseUtil.toMap(request.getInputStream());
		
		System.out.println(userMap);
		List<User> userList = UserData.userList;
		
		User user = User.builder()
				.userId(userList.size() + 1)
				.username((String) userMap.get("username"))
				.password((String) userMap.get("password"))
				.name((String) userMap.get("name"))
				.email((String) userMap.get("email"))
				.build();
		
		userList.add(user);
		
		ResponseUtil.reponse(response).of(201).body(true);
		
		
//		System.out.println(useraMap.get("username"));
//		System.out.println(useraMap.get("password"));
//		System.out.println(useraMap.get("name"));
//		System.out.println(useraMap.get("email"));
		
		
//		ResponseUtil.reponse(response).of(200).body("회원가입 완료!!!");
//		ResponseUtil.reponse(response).of(400).body("회원가입 실패");
		
	}
}








