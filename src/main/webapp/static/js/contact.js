function buildTable() {
    $.getJSON('/api/contact/', {
        ajax: 'true'
    }, function (data) {
        $.each(data, function (index, single) {
            console.log(single.employee)
            $('#contact-table').find('tbody')

                .append("<tr>" +
                "<td>" + single.id + "</td>" +
                "<td>" + single.employee.firstName + " " + single.employee.lastName + "</td>" +
                "<td>" + single.phoneNumber + "</td>" +
                "<td>" + single.email + "</td>" +
                "<td>" + "<button>Edit</button>" + "</td>" +
                "<td>" + "<button>Delete</button>" + "</td>" +
                "</tr>");

        })
    })
}

function populateEmployees() {
    $.getJSON('/api/employee/', {
        ajax: 'true'
    }, function (data) {
    $.each(data, function (index, employee) {
        console.log(employee)
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
    $('#contactModal').show()
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
    console.log(contact)

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