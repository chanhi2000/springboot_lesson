<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String context = request.getContextPath();
%>    
<!DOCTYPE html>
<html>
<!-- 합쳐지고 최소화된 최신 CSS -->

<link rel="stylesheet" href='<%=context %>/css/bootstrap.min.css'>

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="<%=context %>/js/bootstrap.min.js"></script>
<script src="<%=context %>/js/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		alert("document ready");
		
	});
  
</script>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>user</h2>
	<hr/>
	<input type="button" value="버튼" class="btn btn-default" />
	${vo }
</body>  
</html>