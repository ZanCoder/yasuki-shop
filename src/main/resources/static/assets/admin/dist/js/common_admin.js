$('#btnAddProd').on('click', () =>{
    $('#list_prod_order').append(`
        <tr class="even">
            <td class="dtr-control" tabindex="0">1</td>
            <td class="">All others</td>
            <td class="sorting_1">999</td>
            <td class="sorting_1">999</td>
            <td>
            <button class="btn btn-danger btnDelAccount">
                <i class="far fa-trash-alt"></i>
            </button>
            </td>
        </tr>
    `)
});


var urlDeleteUser = window.location.pathname + '/delete';
var urlEditUser = window.location.pathname + '/edit';
var urlSearchUser = window.location.pathname;

//manager user
$('#btnAddAccount').on('click', function(){
    updateTitle('Thêm mới');
    let mode = /*[[${mode}]]*/[];
    if(mode === 'edit'){
        alert(mode)
    }
});
//$('#btnSearch').on('click', function(){
//    let data = {keyword : $('#inputSearch').val()};
//    callAjaxCart(urlSearchUser, 'GET', data);
//});

function deleteUser(id){
    let data = {id : id}
    callAjaxCart(urlDeleteUser, 'DELETE', data);
}
function editUser(id){
    let data = {id : id}
    callAjaxCart(urlEditUser, 'GET', data);
    $('#modalAccount').modal('show');
    updateTitle('Cập nhật');
}
function updateTitle(mode){
    $('.mode').text(mode);
}

function callAjaxCart(url, method, data) {
    $.ajax({
      url: url,
      method: method,
      data: data
    }).then(function(response) {
       if(method != 'GET'){
           window.location.reload();
       }
       updateDataField(response);
    }).fail(function(error) {
       myToastr('error',  error.responseText);
    });
}

function myToastr(type, message){
    Command: toastr[type](message)
    toastr.options = {
      "closeButton": true,
      "debug": true,
      "newestOnTop": true,
      "progressBar": false,
      "positionClass": "toast-top-right",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "2000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
}

function updateDataField(data){
    for (var field in data) {
      if (data.hasOwnProperty(field)) {
        var value = data[field];
        $('[data-field="' + field + '"]').val(value);
      }
    }
}