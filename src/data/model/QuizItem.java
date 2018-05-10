package data.model;

public class QuizItem {
	public String question;
	public String answer;
	
	public QuizItem(String qn, String aw) {
		question = qn;
		answer = aw;
	}

	public QuizItem() {}
	
	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public void setQuestion(String qn) {
		question = qn;
	}

	public void setAnswer(String aw) {
		answer = aw;
	}
}
