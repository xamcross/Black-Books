<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp"%>

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
		<c:forEach items="${cart.orderedBooks}" var="book">
			<tr>
				<td>${book.title }</td>
				<td>${book.description }</td>
				<td>${book.authors }</td>
				<td>${book.price }</td>
				<td><a class="btn btn-danger" href="/checkout/${book.id}">Remove</a></td>
			</tr>
		</c:forEach>
		<tr>
				<td><strong>Total</strong></td>
				<td></td>
				<td></td>
				<td><strong>${cart.totalPrice}</strong></td>
				<td></td>
			</tr>
	</tbody>
</table>