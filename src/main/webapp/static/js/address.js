function buildTable() {
    $.getJSON('/api/address/', {
        ajax: 'true'
    }, function (data) {
        $.each(data, function (index, single) {
            $('#address-table').find('tbody')

                .append("<tr>" +
                    "<td>" + single.id + "</td>" +
                    "<td>" + single.employee.firstName + " " + single.employee.lastName + "</td>" +
                    "<td>" + single.street + "</td>" +
                    "<td>" + single.city + "</td>" +
                    "<td>" + single.zip + "</td>" +
                    "<td>" + "<button onclick='editAddress(" + single.id + ")'>Edit</button>" + "</td>" +
                    "<td>" + "<button data-toggle='modal' data-target='#confirmDeleteModal' data-record-id='" + single.id + "'>Delete</button>" + "</td>" +
                    "</tr>");

        })
    })
}

function populateEmployees() {
    $.getJSON('/api/employee/', {
        ajax: 'true'
    }, function (data) {
        $.each(data, function (index, employee) {
            $('#inputEmployee').find('select')
                .append(
                    "<option value=" + employee.id + ">" + employee.firstName + " " + employee.lastName + "</option>"
                );
        });
    })
}

function insertAddress() {
    //clear fields in modal
    $('#addressId').val("");
    $('#addressVersion').val("");
    $('#inputEmployee').val("");
    $('#inputStreet').val("");
    $('#inputCity').val("");
    $('#inputZip').val("");

    // show modal
    $('#addressModal').modal('toggle');
}

function saveAddress() {
    var id = $('#addressId').val();
    var version = $('#addressVersion').val();
    var employeeId = $('#selectedEmployee').val();
    var street = $('#inputStreetNumber').val();
    var city = $('#inputCity').val();
    var zip = $('#inputZip').val();

    var address = {
        id: id,
        version : version,
        employee: {id: employeeId},
        street: street,
        city: city,
        zip: zip
    }

    $.ajax( {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        type: 'POST',
        data: JSON.stringify(address),
        url: "/api/address/",
        async: true,
        dataType: "json",
        success: function () {
            window.location.reload()
        }
    })
}

function editAddress(id) {
    $.getJSON('/api/address/' + id, {
        ajax: 'true'
    },function (address) {
        $('#addressId').val(address.id);
        $('#addressVersion').val(address.version);
        $('#selectedEmployee').val(address.employee.id);
        $('#inputStreetNumber').val(address.street);
        $('#inputCity').val(address.city);
        $('#inputZip').val(address.zip);

        $('#addressModal').modal('show')

    })
}

document.querySelectorAll("btn-ok").forEach((element) =>{
    element.addEventListener("click", () =>{
    })
});

function deleteModal() {
    $('#confirmDeleteModal').on('click', '.btn-ok', function (e) {
        var $modalDiv = $(e.delegateTarget);

        var id =($(this).data('recordId'));

        $.ajax({
            type: "delete",
            url: "/api/address/" + id,
            async:true,
            dataType: "json",
            success: function () {
                window.location.reload();
            },
            error: function () {
                alert("Error Deleting Address")
            }
        })
    });
    $('#confirmDeleteModal').on('show.bs.modal', function (e) {
        console.log(e);
        var data = $(e.relatedTarget).data();
        $('.btn-ok', this).data('recordId', data.recordId);
    });
}