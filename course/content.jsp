<%@ page contentType="text/html;charset=utf-8" import="java.util.ArrayList, mvc.domain.Course"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset='utf-8'>
<style>
table, th, td {
border: 1px solid white;
border-collapse: collapse;
}
th, td {
padding: 3px;
}
a { text-decoration:none }
</style>
<center>
<hr width='600' size='2' noshade>
<div class="img-box" ><img src="../img/${course.cphoto}" style="margin-left:-80; margin-top:20; margin-bottom:-180;" width="500" height="500" alt="이미지 못 불러옴"></div>
<h1>${course.cname}</h1>
<hr width='600' size='2' noshade>
<table border='1' width='600' align='center' cellpadding='3'>
<c:if test="${empty course}">
	<tr>
		<td colspan="5" style="text-align:center">데이터가 없수꽝</td>
	</tr>
</c:if>

<tr>
<td width='100' align='center'>글번호</td>
<td>${course.cnum}</td>
</tr>
<tr>
<td align='center'>소개</td>
<td>${course.cintro}</td>
</tr>
<tr>
<td align='center'>경로</td>
<td>${course.cnavi}</td>
</tr>
</table>
<hr width='600' size='2' noshade>
<div><a href='course.do?m=list'>뒤로가기</a></div>
</center>