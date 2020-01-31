<%@include file="includes/header.jsp"%>
<%@include file="includes/navbar.jsp"%>

<c:url value="/static/js/address.js" var="address"/>
<script src="${address}"></script>

<script>
    $(document).ready(function () {
        buildTable();
        populateEmployees();
        deleteModal();
    })
</script>

<div class="container">

    <h2>Address List</h2>


    <button onclick="insertAddress()" class="btn btn-default">Add New Address Info</button>

    <table id="address-table" class="table table-striped table-hover">
        <thead>
        <tr>
            <th>Id</th>
            <th>Employee</th>
            <th>Street Address</th>
            <th>City</th>
            <th>Zip Code</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>

    <div id="addressModal" class="modal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Address Details</h4>
                </div>
                <div class="modal-body">

                    <form class="form-horizontal">
                        <fieldset>

                            <hidden id="addressId"/>
                            <hidden id="addressVersion"/>

                            <div class="form-group">
                                <label for="inputEmployee" class="col-lg-2 control-label">Employee</label>
                                <div class="col-lg-10" id="inputEmployee">
                                    <select id="selectedEmployee" type="text" class="form-control" placeholder="Employee">
                                        <option selected value="null">Select Employee</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputStreetNumber" class="col-lg-2 control-label">Street Address</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputStreetNumber" placeholder="Street Number">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputCity" class="col-lg-2 control-label">City</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputCity" placeholder="Email">
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="inputZip" class="col-lg-2 control-label">Zip Code</label>
                                <div class="col-lg-10">
                                    <input type="text" class="form-control" id="inputZip" placeholder="Email">
                                </div>
                            </div>

                        </fieldset>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="saveAddress()">Save changes</button>
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
                    <p>Are you sure you want to delete this Address? This cannot be undone!</p>
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
