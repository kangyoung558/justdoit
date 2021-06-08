<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<jsp:include page="../../../template/header.jsp"/>
	<div class="btns">
		<c:if test="${not empty member}" >
			<a href="write?category=${param.category}"><button>글쓰기</button></a>	
		</c:if>
	</div>
	<div class="tab-list">
		<table>
			<tr>
				<td>글번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
			<c:forEach items="${list}" var="board">
				<tr>
					<td>${board.boardno}</td>
					<td><a href="detail?boardno=${board.boardno}"><c:out value="${board.title}"/></a></td>
					<c:if test="${empty board.username}">
					<td>탈퇴자</td>
					</c:if>
					<c:if test="${!empty board.username}">
					<td>${board.username}</td>
					</c:if>
					<td>${board.regdate}</td>
					<td>${board.hitcount}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
<jsp:include page="../../../template/footer.jsp"/>
