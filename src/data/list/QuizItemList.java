package data.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import data.model.QuizItem;

public class QuizItemList {
	private List<QuizItem> quizItems;
	private int size;
	private final static QuizItem EMPTY_QUIZ_ITEM = new QuizItem("", "");
	
	public QuizItemList(List<QuizItem> quizItems) {
		if (quizItems == null) {
			quizItems = new ArrayList<QuizItem>();
		} else {
			this.quizItems = quizItems;
		}
		
		this.size = this.quizItems.size();
	}
	
	public QuizItem get(int index) {
		if (index >= size || index < 0) {
			return EMPTY_QUIZ_ITEM;
		}
		return quizItems.get(index);
	}
	
	public int size() {
		return size;
	}
	
	public void shuffle() {
		Collections.shuffle(quizItems);
	}
}
