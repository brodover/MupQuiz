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
	    console.log(question);
	}
	quizSet = data.quizSet;
	totalQn = data.quizSet.length;
	currentQn = 0;
	score = 0;
	isQnLocked = false;
	
	console.log("totalQn: " + totalQn);
	displayQuestion();
}

function displayQuestion() {
	console.log("displayQuestion current: " + currentQn);
	clearDisplay();
	
	var questionTxt = document.querySelector(stage + " .questionText");
	questionTxt.textContent = quizSet[currentQn];
	
	var submitBtn = document.querySelector(stage + " .submit");
	submitBtn.onclick = submitClicked;
}//display question

function changeQuestion() {
	if (stage == "#game1") {
		stage2 = "#game1";
		stage = "#game2";
	} else {
		stage2 = "#game2";
		stage = "#game1";
	}
	
	var isFinished = currentQn >= totalQn;
	console.log(currentQn + ", " + totalQn);
	if (isFinished) {
		displayEnd();
	} else {
		displayQuestion();
	}
	
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
	$.get('Reset', function(data) {});
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

function displayEnd() {
	console.log("end");
	var end = document.querySelector(stage2 + " .end");
	end.setAttribute('class', 'end nodisplay');
	
	var resetBtn = document.querySelector(stage2 + " .reset");
	resetBtn.onclick = resetClicked;
}

Date.prototype.timeNow = function () {
    return ((this.getHours() < 10)?"0":"") + this.getHours() +":"+ ((this.getMinutes() < 10)?"0":"") + this.getMinutes() +":"+ ((this.getSeconds() < 10)?"0":"") + this.getSeconds();
}