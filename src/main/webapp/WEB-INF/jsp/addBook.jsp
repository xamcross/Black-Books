<%@ include file="../layout/taglib.jsp" %>

<form:form class="form-horizontal" commandName="book">
<div class="form-group">
    <label for="title" class="col-sm-2 control-label">Title</label>
    <div class="col-sm-10">
      <form:input path="title" class="form-control" placeholder="Title"/>
    </div>
  </div>
  <div class="form-group">
    <label for="description" class="col-sm-2 control-label">Description</label>
    <div class="col-sm-10">
      <form:input path="description" class="form-control" placeholder="Description"/>
    </div>
  </div>
  <div class="form-group">
    <label for="authors" class="col-sm-2 control-label">Authors</label>
    <div class="col-sm-10">
      <form:input path="authors" class="form-control" placeholder="Author(s)"/>
    </div>
  </div>
  <div class="form-group">
    <label for="price" class="col-sm-2 control-label">Price</label>
    <div class="col-sm-10">
      <input name="price" type="number" min="0" step="0.01" class="form-control" placeholder="Price, $"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-2">
      <input type="submit" class="btn btn-lg btn-primary"  value="Add book"/>
    </div>
  </div>
</form:form>