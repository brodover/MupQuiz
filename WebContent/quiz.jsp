<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML>
<html>
<head>
	<title>mup's quiz</title>
	<link href="main.css" rel="stylesheet" type="text/css" />
	<script src="jquery.js"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var questionLock=false;
		var score=0;
		
		displayQuestion();

		function displayQuestion() {
			var isFirst = getBoolAttr('${first}');
			if (isFirst == true) {
				changeQuestion();
			} else {
				setTimeout(function(){ changeQuestion() }, 900);
			}
		}//display question
		
		function changeQuestion() {
			var stage="#game1";
			var stage2="#game2";
			
			var isFinished = getBoolAttr('${finished}');
			if (isFinished) {
				var score = '${score}';
				var maxQn = '${totalQn}';
				$(stage2).empty();
				$(stage2).append("<div class='questionText' >You have finished the quiz!<br><br>Total questions:\t"+maxQn+"<br>Correct answers:\t"+score+"</div><br><div><button class='reset'>Reset</button></div>");
				$(".reset").click(resetClicked);
			}
			
			$(stage).animate({"right": "+=800px"}, "slow", function() { $(stage).css('right' , '-800px'); $(stage).empty(); });
			$(stage2).animate({"right": "+=800px"}, "slow", function() { questionLock=false;} );
		}//change question
		
		function resetClicked() {
			location.href = "./reset";
		}
		
		function getBoolAttr(attr) {
			var a = attr;
			return a == 'true';
		}
		
	});
	</script>
</head>
<body>
	<div id="topbar">Country Quiz</div>
	<div class="spacer"></div>
	<div id="navContent">
		<div id="game1">
			<c:if test = "${!first}">
			<div class = "questionText"><c:out value = "${quizItem1.question}" /></div>
			<div class="answer">
				<form class="input-container" action="quiz" method="post">
					<input class="target" type="text" name="answer" disabled /><input class="submit" type="submit" value="Submit" disabled />
				</form>
			</div>
			<c:if test = "${checked}">
				<div class="feedback1">CORRECT</div>
			</c:if>
			<c:if test = "${!checked}">
				<div class="feedback2">WRONG</div>
			</c:if>
			</c:if>
		</div>
		<div id="game2">
			<div class = "questionText"><c:out value = "${quizItem2.question}" /></div>
			<div class="answer">
				<form class="input-container" action="quiz" method="post">
					<input class="target" type="text" name="answer" /><input class="submit" type="submit" value="Submit" />
				</form>
			</div>
		</div>
	</div>
</body>
</html>