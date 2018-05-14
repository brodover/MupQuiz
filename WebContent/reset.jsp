<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>mup's quiz</title>
	<script src="jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$.get('Reset', function(data) {
				location.href = "/mupquiz";;
		    });
		});
		
	</script>
</head>
<body>

</body>
</html>