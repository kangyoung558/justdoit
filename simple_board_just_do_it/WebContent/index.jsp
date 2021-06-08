<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="template/header.jsp"></jsp:include>

<div class="content">	
	<table>
		<caption>자유게시판</caption>
			<c:forEach items="${list}" var="board" end="5">
				<tr>
					<td><a href="board/detail?boardno=${board.boardno}"><c:out value="${board.title}"/></a></td>
					<td>${board.regdate}</td>
				</tr>
			</c:forEach>
	</table>
	<table>
		<caption>공지사항</caption>
			<c:forEach items="${list2}" var="board" end="5">
				<tr>
					<td><a href="board/detail?boardno=${board.boardno}"><c:out value="${board.title}"/></a></td>
					<td>${board.regdate}</td>
				</tr>
			</c:forEach>
	</table>
	<table>
		<caption>QnA</caption>
			<c:forEach items="${list3}" var="board" end="5">
				<tr>
					<td><a href="board/detail?boardno=${board.boardno}"><c:out value="${board.title}"/></a></td>
					<td>${board.regdate}</td>
				</tr>
			</c:forEach>
	</table>
	<table>
		<caption>자료실</caption>
			<c:forEach items="${list4}" var="board" end="5">
				<tr>
					<td><a href="board/detail?boardno=${board.boardno}"><c:out value="${board.title}"/></a></td>
					<td>${board.regdate}</td>
				</tr>
			</c:forEach>
	</table>
</div>


<jsp:include page="template/footer.jsp"></jsp:include>
