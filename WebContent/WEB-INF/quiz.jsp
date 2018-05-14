<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML>
<html>
<head>
	<title>mup's quiz</title>
	<link href="main.css" rel="stylesheet" type="text/css" />
	<script src="jquery.js"></script>
	<script src="controller.js"></script>
</head>
<body>
	<div id="topbar">Country Quiz</div>
	<div class="spacer"></div>
	<div id="navContent">
		<div id="game1">
			<div class = "quiz">
				<div class = "questionText text"></div>
				<div class="answer">
					<form class="input-container">
						<input class="target" type="text" name="answer" /><button class="submit">Submit</button>
					</form>
				</div>
				<div class="feedback1">CORRECT</div>
				<div class="feedback2">WRONG</div>
			</div>
			<div class = "end" >
				<div class = "endText text">
					You have finished the quiz!<br><br>Total questions:\t"+totalQn+"<br>Correct answers:\t"+score+"
				</div><br><br><button class='reset'>Reset</button>
			</div>
		</div>
		<div id="game2">
			<div class = "quiz">
				<div class = "questionText text"></div>
				<div class="answer">
					<form class="input-container">
						<input class="target" type="text" name="answer" /><button class="submit">Submit</button>
					</form>
				</div>
				<div class="feedback1">CORRECT</div>
				<div class="feedback2">WRONG</div>
			</div>
			<div class = "end" >
				<div class = "endText text">
					You have finished the quiz!<br><br>Total questions:\t"+totalQn+"<br>Correct answers:\t"+score+"
				</div><br><br><button class='reset'>Reset</button>
			</div>
		</div>
	</div>
</body>
</html>