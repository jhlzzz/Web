<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<style>
	table, th, td {
	   border: 1px solid black;
	   border-collapse: collapse;
	}
	th, td {
	   padding: 5px;
	}
	/* 22.03.12 text 가운데 정렬 */
	td {
		text-align: center;
	}
	a { text-decoration:none }
</style>
<meta charset="utf-8">
<center>
	<font color='gray' size='4' face='휴먼편지체'>
	<hr width='600' size='2' color='gray' noshade>
	<h3>자주묻는질문</h3>
	<font color='gray' size='4' face='휴먼편지체'>
	<a href='write.do'>글쓰기</a>
	</font>
	<hr width='600' size='2' color='gray' noshade>
	</font>
	<table border='2' width='600' align='center' noshade>
		<tr>
			<th width='20%'>No</th>
			<th>Subject</th>
		</tr>
		<tr>
			<td>${faq.seq}</td>
			<td>${faq.subject}</td>
		</tr>
		<tr>
			<!-- content가 no와 subject 병합셀사이즈로 보이게, content는 왼쪽정렬 -->
			<td colspan="2" style='text-align:left;'>${faq.content}</td>
		</tr>
	</table>
	
	<hr width='600' size='2' color='gray' noshade>
	<font color='gray' size='4' face='휴먼편지체'>
	<a href='update.do?seq=${faq.seq}'>수정</a>
	 &nbsp;&nbsp;| 
	<a href='del.do?seq=${faq.seq}'>삭제</a>
	 &nbsp;&nbsp;| 
	<a href='list.do'>목록</a>
	</font>
	<hr width='600' size='2' color='gray' noshade>
</center>
