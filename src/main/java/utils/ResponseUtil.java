package utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;abstract


public class ResponseUtil {
	
	public static ResponseUtilBuilder reponse(HttpServletResponse response) {
		return new ResponseUtilBuilder(response);
	}
	
	public static class ResponseUtilBuilder {
		
		private HttpServletResponse response;
		
		public ResponseUtilBuilder(HttpServletResponse response) {
			this.response = response;
		}
		
		public ResponseUtilBuilder of(int satausCode) {
			response.setStatus(satausCode);
			return this;
		}
		
		public void body(Object body) throws IOException {
			// response시 항상 동일
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().println(body);
			
		}
	}
}
