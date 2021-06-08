
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>2021. 3. 22. 오후 3:33:05</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
		<input type="file" name="file1">
		<input type="file" name="file2">
		<input type="file" name="file3">
		<button>전송</button>
	</form>
	<%
	 	Enumeration<String> headerNames	= request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			out.println(headerName + ":::" + headerValue + "<br>");
		}
		
		out.println(request.getHeader("user-agent").toLowerCase().contains("trident") ? "IE" : "IE X" );
	
	%>
	
	<%
		if(request.getMethod().equals("POST")) {
			String uploadPath = request.getRealPath("/upload");
			String today= new SimpleDateFormat("yyMMdd").format(new Date());
			uploadPath = uploadPath + File.separator + today;
			File file = new File(uploadPath);
			
			if(!file.exists()){
				file.mkdirs();
			}
			//upload/210322/
			int maxSize = 10*1024*1024;
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, "utf-8", new DefaultFileRenamePolicy());
		}
	%>
</body>
</html>