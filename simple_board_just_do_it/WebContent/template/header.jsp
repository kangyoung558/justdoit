<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>JUST Do It</title>
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&family=Noto+Serif+KR:wght@200;300;400;500;600;700;900&display=swap" rel="stylesheet">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
	<link rel="stylesheet" href="/member_board/assets/css/common.css">
	<script src="https://cdn.ckeditor.com/ckeditor5/11.0.1/classic/ckeditor.js"></script>
</head>
<body>
	<%
		String idsave = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null)
		for(Cookie cookie : request.getCookies()){
			if(cookie.getName().equals("idsave")) {
				idsave = cookie.getValue();
			}
		}
	%>
	<div class="wrapper">
	<header class="header">
		 <h1><a href="<%=request.getContextPath() %>/index">JUST Do It</a></h1>
		<form method="post" action="<%=request.getContextPath() %>/login">
			<ul>
				<c:if test="${empty member}">
					<li><label>아이디 <input type="text" name="id" value="${cookie.idsave.value}"> </label></li>
					<li><label>비밀번호 <input type="password" name="pw"> </label></li>
					<li>
					<label>아이디 저장 <input type="checkbox" name="idsave" ${empty cookie.idsave.value ? '' : 'checked'} class="ckbox"></label>
					</li>
					<li><label><button>로그인</button></label></li>
					<li><a href="<%=request.getContextPath()%>/join"><button type="button">회원가입</button></a></li>
				</c:if>	
				<c:if test="${!empty member}">
					<li>${member.name}님 환영합니다.<button formaction="<%=request.getContextPath() %>/logout">로그아웃</button><a href="<%=request.getContextPath()%>/info"><button type="button">회원정보</button></a></li>
				</c:if>
			</ul>		
		</form>
		<script>
			$("<input>").attr({type:"hidden", name:"uri", value:location.pathname+location.search}).appendTo("form");
		</script>
	</header>
	<nav class="gnb">
		<ul>
			<li><a href="<%=request.getContextPath()%>/info1">프로그래밍이란?</a></li>
			<li><a href="<%=request.getContextPath()%>/info2">개발자가 되려면?</a></li>
			<li><a href="<%=request.getContextPath()%>/board/list?category=1">자유게시판</a></li>
			<li><a href="<%=request.getContextPath()%>/board/list?category=2">공지사항</a></li>
			<li><a href="<%=request.getContextPath()%>/board/list?category=3">Q/A</a></li>
			<li><a href="<%=request.getContextPath()%>/board/list?category=4">자료실</a></li>
		</ul>
	</nav>