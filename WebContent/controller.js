var stage="#game1";
var stage2="#game2";

var quizSet;
var totalQn;
var currentQn;
var score;
var isQnLocked;

$(document).ready(function() {
	$.get('GetQuiz', function(data) {
		dataLoaded(data);
    });
});

function dataLoaded(data){
	for (var i = 0; i < data.quizSet.length; i++) {
	    var question = data.quizSet[i];
	}
	quizSet = data.quizSet;
	totalQn = data.quizSet.length;
	currentQn = data.currentQn;
	score = data.score;
	isQnLocked = false;
	
	display();
}

function display() {
	var isFinished = currentQn >= totalQn;
	if (isFinished) {
		displayEnd(stage);
	} else {
		displayQuestion();
	}
}

function displayQuestion() {
	clearDisplay();
	
	var questionTxt = document.querySelector(stage + " .questionText");
	questionTxt.textContent = quizSet[currentQn];
	
	var submitBtn = document.querySelector(stage + " .submit");
	submitBtn.onclick = submitClicked;
}//display question

function displayEnd(endStage) {
	var endText = document.querySelector(endStage + " .endText");
	endText.innerHTML = "You have finished the quiz!<br><br>Total questions:\t"+totalQn+"<br>Correct answers:\t"+score;
	
	var stage = document.querySelector(endStage + " .quiz");
	stage.setAttribute('class', 'quiz nodisplay');
	
	var end = document.querySelector(endStage + " .end");
	end.setAttribute('class', 'end');
	
	var resetBtn = document.querySelector(endStage + " .reset");
	resetBtn.onclick = resetClicked;
}

function changeQuestion() {
	if (stage == "#game1") {
		stage2 = "#game1";
		stage = "#game2";
	} else {
		stage2 = "#game2";
		stage = "#game1";
	}
	
	display();
	
	$(stage).animate({"right": "+=800px"}, "slow", function() { });
	$(stage2).animate({"right": "+=800px"}, "slow", function() { $(stage2).css('right' , '-800px'); isQnLocked=false;} );
}//change question

function submitClicked(e) {
	e.preventDefault();
	if (!isQnLocked) {
		isQnLocked = true;
		
		var searchTerm = document.querySelector(stage + " input[name='answer']");
		
		$.post('CheckAnswer', { answer: searchTerm.value })
		.done(function(data) {
			currentQn = data.currentQn;
			score = data.score;
			var isCorrect = data.checked;
			
			var currentStage = document.querySelector(stage);
			var correct = currentStage.querySelector(".feedback1");
			var wrong = currentStage.querySelector(".feedback2");
			if (isCorrect) {
				correct.setAttribute('class', 'feedback1');
				wrong.setAttribute('class', 'feedback2 nodisplay');
			} else {
				correct.setAttribute('class', 'feedback1 nodisplay');
				wrong.setAttribute('class', 'feedback2');
			}
			setTimeout(function(){changeQuestion()},900);
	    });
	}
}

function resetClicked() {
	window.location = "reset.jsp";
}

function showCorrect() {
	var correct = document.querySelector(stage + " .feedback1");
	var wrong = document.querySelector(stage + " .feedback2");
	
	correct.setAttribute('class', 'feedback1');
	wrong.setAttribute('class', 'feedback2 nodisplay');
}

function showWrong() {
	var correct = document.querySelector(stage + " .feedback1");
	var wrong = document.querySelector(stage + " .feedback2");
	
	correct.setAttribute('class', 'feedback1');
	wrong.setAttribute('class', 'feedback2 nodisplay');
}

function clearDisplay() {
	var correct = document.querySelector(stage + " .feedback1");
	var wrong = document.querySelector(stage + " .feedback2");
	var end = document.querySelector(stage + " .end");
	
	correct.setAttribute('class', 'feedback1 nodisplay');
	wrong.setAttribute('class', 'feedback2 nodisplay');
	end.setAttribute('class', 'end nodisplay');

	var searchTerm = document.querySelector(stage + " input[name='answer']");
	searchTerm.value = "";
}

Date.prototype.timeNow = function () {
    return ((this.getHours() < 10)?"0":"") + this.getHours() +":"+ ((this.getMinutes() < 10)?"0":"") + this.getMinutes() +":"+ ((this.getSeconds() < 10)?"0":"") + this.getSeconds();
}