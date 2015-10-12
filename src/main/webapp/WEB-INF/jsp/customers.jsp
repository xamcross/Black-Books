<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/taglib.jsp" %>


<table class="table table-hover table-stripped">
	<thead>
		<tr>
			<th>Customer email:</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${customers}" var="customer">
			<tr>
				<td><a href='<spring:url value="/customers/${customer.id}"/>'>${customer.email}</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
