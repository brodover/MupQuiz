package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import logic.Controller;

@WebServlet("/CheckAnswer")
public class CheckAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	
    @Override
    public void init() throws ServletException {
    	controller = Controller.getInstance();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aw = request.getParameter("answer");
		controller.submitClicked(aw);
		
		String message;
		JSONObject json = new JSONObject();
		json.put("checked", controller.isCorrect());
		json.put("score", controller.getScore());
		json.put("currentQn", controller.getCurrentQnNum());
		message = json.toString();
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
	}

}
