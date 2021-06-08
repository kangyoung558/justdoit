<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<jsp:include page="../../../template/header.jsp"/>
	<form method="post" enctype="multipart/form-data">
		<h1>${title}</h1>
		<table class="write-table">
			<tr>
				<th>제목</th>
				<td colspan="5"><input type="text" name="title" value="${board.title}"></td>
			</tr>
		
			<tr>
				<th>글내용</th>
				<td colspan="5">
					<textarea rows="10" cols="30" name="content"  id="editor">${board.content}</textarea>
				</td>
			</tr>
			
			<c:if test="${param.category == 4}">
				<tr>
					<td colspan="6"><input type="file" name="file1"></td>
				</tr>
				<tr>
					<td colspan="6"><input type="file" name="file2"></td>
				</tr>
				<tr>
					<td colspan="6"><input type="file" name="file3"></td>
				</tr>
			</c:if>
		</table>
		<div class="btns">
			<button ${title == '글수정' ? 'formenctype="application/x-www-form-urlencoded"' : ''}>${title}</button>
			<button type="button" class="do-prev">작성취소</button>
		</div>
		<input type="hidden" name="boardno" value="${param.boardno}">
		<input type="hidden" name="category" value="${param.category}">
		<%-- <input type="hidden" name="userid" value="${member.userid}"> --%>
	</form>
	 <script>
	    ClassicEditor
	        .create( document.querySelector( '#editor' ), {
	            // 제거 하고싶은 플러그인 (배열)
	             removePlugins: [ 'ImageUpload' ]
	        } )
	        .catch(function(error){
	            console.error( error );
	        } );
    </script>
	
<jsp:include page="../../../template/footer.jsp"/>
