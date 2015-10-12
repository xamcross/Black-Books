<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../layout/taglib.jsp"%>

<h1>Login: ${customer.email }</h1>
<h2>Password: ${customer.password }</h2>

<table class="table table-hover table-stripped">
	<thead>
		<tr>
			<th>Title:</th>
			<th>Description:</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cart.orderedBooks}" var="book">
			<tr>
				<td>${book.title }</td>
				<td>${book.description }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
