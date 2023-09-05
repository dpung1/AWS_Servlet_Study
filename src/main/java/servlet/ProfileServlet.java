package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.UserData;
import entity.User;
import security.Authentication;
import security.SecurityContextHolder;
import utils.JsonParseUtil;
import utils.ResponseUtil;

@WebServlet("/mypage/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String token = request.getHeader("Authorization");
		
		User user = SecurityContextHolder.findAuthenticationByToken(token).getUser();
		
		ResponseUtil.reponse(response).of(200).body(JsonParseUtil.toJson(user));
	}
	
	// 정보 수정
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> profileMap = JsonParseUtil.toMap(request.getInputStream());
		
		Authentication authentication = SecurityContextHolder.findAuthenticationByToken(request.getHeader("Authorization"));
		
		User oldUser = authentication.getUser();
		
		List<User> userList = UserData.userList;
				
		User user = User.builder()
				// 기존의 유저ID를 가지고 와야함 
				.userId(oldUser.getUserId())
				.username((String) profileMap.get("username"))
				.password((String) profileMap.get("password"))
				.name((String) profileMap.get("name"))
				.email((String) profileMap.get("email"))
				.build();
		
		for(int i = 0; i < userList.size(); i++) {
			if(userList.get(i).getUserId() == user.getUserId()) {
				userList.set(i, user);
				authentication.setUser(user);
				ResponseUtil.reponse(response).of(200).body(true);
			}
		}
		
		ResponseUtil.reponse(response).of(200).body(false);
	}
}








