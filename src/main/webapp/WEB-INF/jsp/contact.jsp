<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

<c:url value="/static/js/contact.js" var="contact"/>
<script src="${contact}"></script>

<script>
    $(document).ready(function () {
        buildTable();
        populateEmployees();
        deleteModal();
    })
</script>

<div class="container">

    <h2>Contact List</h2>


    <button onclick="insertContact()" class="btn btn-default">Add New Contact Info</button>

    <table id="contact-table" class="table table-striped table-hover">
        <thead>
            <tr>
                <th>Id</th>
                <th>Employee</th>
                <th>Phone Number</th>
                <th>Email</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>

    <div id="contactModal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Contact Details</h4>
                </div>
                <div class="modal-body">

                    <form class="form-horizontal">
                        <fieldset>

                            <hidden id="contactId"/>
                            <hidden id="contactVersion"/>

                            <div class="form-group">
                                <label for="inputEmployee" class="col-lg-2 control-label">Employee</label>
                                <div class="col-lg-10" id="inputEmployee">
                                    <select id="selectedEmployee" type="text" class="form-control" placeholder="Employee">
                                        <option selected value="null">Select Employee</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputPhoneNumber" class="col-lg-2 control-label">Phone Number</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputPhoneNumber" placeholder="Phone Number">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputEmail" placeholder="Email">
                                </div>
                            </div>

                        </fieldset>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveContact()">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <div id="confirmDeleteModal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Confirm Delete</h4>
                </div>
                <div class="modal-body">
                    <p>Are you sure you want to delete this Contact? This cannot be undone!</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" class="btn btn-danger btn-ok" data-dismiss="modal" id="confirmDelete">Delete</button>
                </div>
            </div>
        </div>
    </div>

</div>

<%@include file="includes/footer.jsp"%>
