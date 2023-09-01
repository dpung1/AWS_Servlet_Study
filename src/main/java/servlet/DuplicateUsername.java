package servlet;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ResponseUtil;

@WebServlet("/auth/signup/duplicate/username")
public class DuplicateUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String[] usernames = {"aaa", "bbb", "ccc"};
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		
		for(int i = 0; i < usernames.length; i++) {
			if(Objects.equals(usernames[i], username)) {
				ResponseUtil.reponse(response).of(400).body(true);
				return;
			}
		}
		
		ResponseUtil.reponse(response).of(200).body(false);
	}
}







