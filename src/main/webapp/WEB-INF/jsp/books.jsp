<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

<c:if test="${param.bookAdded eq true }">
	<div class="alert alert-success">Book successfully added to cart</div>
</c:if>
<table class="table table-hover table-stripped">
	<thead>
		<tr>
			<th>Title:</th>
			<th>Description:</th>
			<th>Author(s)</th>
			<th>Price, $:</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${books}" var="book">
			<tr>
				<td>${book.title }</td>
				<td>${book.description }</td>
				<td>${book.authors }</td>
				<td>${book.price }</td>
				<td><form action='<c:url value="/books/${book.id}"/>'
						method="POST">
						<input class="btn" type="submit" value="Add to cart" />
					</form></td>
			</tr>
		</c:forEach>
	</tbody>
</table>