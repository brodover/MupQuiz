package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.Controller;

@WebServlet("/GetQuiz")
public class GetQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Controller controller;

    @Override
    public void init() throws ServletException {
    	controller = Controller.getInstance();
//    	private BookDBAO bookDB;
//        public void init() throws ServletException {
//            bookDB = (BookDBAO)getServletContext().
//                getAttribute("bookDB");
//            if (bookDB == null) throw new
//                UnavailableException("Couldn��t get database.");
//        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message = controller.makeQuizSetINJsonString();
		controller.softReset();
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
		
		// Will be available as ${<string>} in JSP
//        request.setAttribute("quizItem1", controller.getQuizItem(-1));
//        request.setAttribute("quizItem2", current);
//        request.setAttribute("first", controller.isFirst());
//        request.setAttribute("finished", controller.isFinished());
//        request.setAttribute("totalQn", controller.getTotalQuestions());
//        response.sendRedirect("/mupquiz/quiz.jsp");
//        request.getRequestDispatcher("/quiz.jsp").forward(request, response);
//        response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
