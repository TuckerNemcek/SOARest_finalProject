function buildTable() {
    $.getJSON('/api/contact/', {
        ajax: 'true'
    }, function (data) {
        $.each(data, function (index, single) {
            $('#contact-table').find('tbody')

                .append("<tr>" +
                "<td>" + single.id + "</td>" +
                "<td>" + single.employee.firstName + " " + single.employee.lastName + "</td>" +
                "<td>" + single.phoneNumber + "</td>" +
                "<td>" + single.email + "</td>" +
                "<td>" + "<button onclick='editContact(" + single.id + ")'>Edit</button>" + "</td>" +
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

function insertContact() {
    //clear fields in modal
    $('#contactId').val("");
    $('#contactVersion').val("");
    $('#inputEmployee').val("");
    $('#inputEmail').val("");
    // show modal
    $('#contactModal').modal('toggle');
}

function saveContact() {
var id = $('#contactId').val();
var version = $('#contactVersion').val();
var employeeId = $('#selectedEmployee').val();
var phoneNumber = $('#inputPhoneNumber').val();
var email = $('#inputEmail').val();

var contact = {
    id: id,
    version : version,
    employee: {id: employeeId},
    phoneNumber: phoneNumber,
    email: email
}

    $.ajax( {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        type: 'POST',
        data: JSON.stringify(contact),
        url: "/api/contact/",
        async: true,
        dataType: "json",
        success: function () {
            window.location.reload()
        }
    })
}

function editContact(id) {
    $.getJSON('/api/contact/' + id, {
        ajax: 'true'
    },function (contact) {
       $('#contactId').val(contact.id);
       $('#contactVersion').val(contact.version);
       $('#selectedEmployee').val(id);
       $('#inputPhoneNumber').val(contact.phoneNumber);
       $('#inputEmail').val(contact.email);

       $('#contactModal').modal('show')

    })
}

document.querySelectorAll("btn-ok").forEach((element) =>{
    element.addEventListener("click", () =>{
        console.log("Clicked!!!!!");
    })
});

function deleteModal() {
    $('#confirmDeleteModal').on('click', '.btn-ok', function (e) {
    var $modalDiv = $(e.delegateTarget);

    var id =($(this).data('recordId'));

    $.ajax({
        type: "delete",
        url: "/api/contact/" + id,
        async:true,
        dataType: "json",
        success: function () {
            window.location.reload();
        },
        error: function () {
            alert("Error Deleting Contact")
        }
    })
});
    $('#confirmDeleteModal').on('show.bs.modal', function (e) {
        var data = $(e.relatedTarget).data();
        $('.btn-ok', this).data('recordId', data.recordId);
    });
}