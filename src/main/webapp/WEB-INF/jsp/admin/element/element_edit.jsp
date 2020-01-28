<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../../includes/header.jsp"%>
<%@include file="../../includes/navbar.jsp"%>
<%@include file="../../includes/subnav_admin.jsp"%>

<script>

    $(document).ready(function () {
        //attach an onclick function to the remove buttons
        $('.remove_button').click(function () {
            //log name button, and contents of associated text box
            console.log(this.name);
            console.log($('#' + this.name).val());
            //clear the value / contents of the text box
            $('#' + this.name).val('')
            //submit the form
            $('#elementType').submit();
        })

    });
</script>

<div class="wrapper">
    <%--    SIDEBAR HERE--%>
    <%@include file="element_sidebar.jsp"%>

    <div id="main-wrapper" class="col-sm-10">
        <div class="col-sm-8">

            <c:set var="idx" value="0" scope="page"></c:set>
          <form:form cssClass="form-horizontal" modelAttribute="elementType" action="/admin/element/update/" method="post">
            <form:hidden path="id" />
            <form:hidden path="version" />

              <div class="row">
                  <div class="form-group">
                      <label for="inputElementTypeName" class="col-sm-2 control-label">Element Type</label>
                      <div class="col-sm-8">
                        <form:input path="elementTypeName" type="text" id="inputElementTypeName" cssClass="form-control"></form:input>
                      </div>
                  </div>
              </div>

              <div class="row">
                  <div class="col-sm-10">
                      <hr>
                  </div>
              </div>

              <for:forEach items="${elementType.elementList}" var="element">
                  <form:hidden path="elementList[${idx}].id"/>
                  <form:hidden path="elementList[${idx}].version"/>
                  <div class="row">
                      <div class="form-group">
                          <label class="col-sm-3 control-label" for="${idx}">Element</label>
                              <div class="col-sm-7">
                                  <div class="input-group">
                                      <form:input path="elementList[${idx}].elementName" id="${idx}" cssClass="form-control" />
                                          <span class="input-group-btn">
                                              <button name="${idx}" class="btn btn-default remove_button" type="button">Remove</button>
                                          </span>
                                  </div>
                              </div>
                      </div>
                  </div>
                    <c:set var="idx" value="${idx +1}" scope="page" />
              </for:forEach>

              <div class="row">
                  <div class="form-group">
                      <label for="inputNewElement" class="col-sm-3 control-label">Add New Element</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="inputNewElement" name="inputNewElement" />
                        </div>
                  </div>
              </div>

              <div class="row">
                <button class="btn btn-primary">Update</button>
              </div>

          </form:form>

        </div>

        <div class="col-sm-4">

<%--          SUCCESS  ALERTS--%>
            <div class="${successAlert == null ? 'hidden' : successAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-success">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Well done!</strong> You successfully read <a href="#" class="alert-link">this important alert message</a>
                </div>
            </div>

    <%--          WARNING  ALERTS--%>
            <div class="${warningAlert == null ? 'hidden' : warningAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-warning">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Oh snap!</strong> <a href="#" class="alert-link">Change a few thing up</a> and try submitting again
                </div>
            </div>

<%--            ERROR ALERT--%>
            <div class="${errorAlert == null ? 'hidden' : errorAlert}" id="successAlert">
                <div class="alert alert-dismissible alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Oh snap!</strong> <a href="#" class="alert-link">Change a few things up</a> and try submitting again.
                </div>
            </div>


    </div>
    </div>

</div>

<%@include file="../../includes/footer.jsp"%>
