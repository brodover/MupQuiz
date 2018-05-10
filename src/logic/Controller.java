package logic;

import java.sql.SQLException;
import java.util.Locale;

import javax.servlet.ServletException;

import data.dao.QuizItemDao;
import data.list.QuizItemList;
import data.model.QuizItem;

public class Controller {
	private static Controller instance = null;
	private QuizItemDao quizItemDao;
	private QuizItemList quizItems;

	private int maxQuestion;
	private int questionNumber = 0;
	private int score = 0;
	private boolean isCorrect = false;
	
	private final static int MAX_QUESTION = 2;
	
	protected Controller() {
		quizItemDao = new QuizItemDao();
	}
	
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		
		return instance;
	}

	public void init() throws ServletException {
		if (isFirst()) {
			loadFromDB();
			quizItems.shuffle();
		}
	}
	
	public void loadFromDB() throws ServletException {
		try {
			quizItems = new QuizItemList(quizItemDao.list());
			maxQuestion = maxQuestion > quizItems.size() ? quizItems.size() : MAX_QUESTION;
        } catch (SQLException e) {
            throw new ServletException("Cannot obtain items from DB", e);
        }
	}

	public void submitClicked(String userAw) {
		userAw = userAw.toLowerCase(Locale.ROOT);
		String correctAw = getQuizItem().getAnswer().toLowerCase(Locale.ROOT);
		isCorrect = correctAw.equals(userAw);
		if (isCorrect) score++;
		questionNumber++;
	}
	
	public void reset() {
		questionNumber = 0;
		score = 0;
		quizItems.shuffle();
	}
	
	public QuizItem getQuizItem(int increment) {
		int qnNum = questionNumber + increment;
		return quizItems.get(qnNum);
	}

	public QuizItem getQuizItem() {
		return getQuizItem(0);
	}
	
	public int getCurrentQnNum() {
		return questionNumber;
	}
	
	public int getScore() {
		return score;
	}

	public int getTotalQuestions() {
		return maxQuestion;
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}
	
	public boolean isFirst() {
		return questionNumber == 0;
	}
	
	public boolean isFinished() {
		return questionNumber >= maxQuestion;
	}
	
}
