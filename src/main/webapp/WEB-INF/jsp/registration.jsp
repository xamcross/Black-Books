<%@ include file="../layout/taglib.jsp" %>

<form:form class="form-horizontal" commandName="customer">
<div class="form-group">
    <label for="email" class="col-sm-2 control-label">Email</label>
    <div class="col-sm-10">
      <form:input path="email" class="form-control" placeholder="Email"/>
    </div>
  </div>
  <div class="form-group">
    <label for="password" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <form:password path="password" class="form-control" placeholder="Password"/>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-2">
      <input type="submit" class="btn btn-lg btn-primary"  value="Register"/>
    </div>
  </div>
</form:form>