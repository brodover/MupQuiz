package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.model.QuizItem;
import logic.Controller;

/**
 * Servlet implementation class GetQuiz
 */
@WebServlet("/GetQuiz")
public class GetQuiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Controller controller;

    @Override
    public void init() throws ServletException {
    	System.out.println("mup init");
    	controller = Controller.getInstance();
    	controller.init();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		QuizItem current = controller.getQuizItem(0);
		System.out.println("mup get: " + controller.getCurrentQnNum() + ", " + current.getQuestion() + ", " + current.getAnswer());
		// Will be available as ${<string>} in JSP
        request.setAttribute("quizItem1", controller.getQuizItem(-1));
        request.setAttribute("quizItem2", current);
        request.setAttribute("checked", controller.isCorrect());
        request.setAttribute("first", controller.isFirst());
        request.setAttribute("finished", controller.isFinished());
        request.setAttribute("score", controller.getScore());
        request.setAttribute("totalQn", controller.getTotalQuestions());
        request.getRequestDispatcher("/quiz.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		String aw = request.getParameter("answer");
        System.out.println("mup post: " + aw);
		controller.submitClicked(aw);
		
        doGet(request, response);
    }
}
