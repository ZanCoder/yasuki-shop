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


var urlDelete = window.location.pathname + '/delete';
var urlEdit = window.location.pathname + '/edit';
var urlSearch = window.location.pathname;
var urlChangeStatus = window.location.pathname + '/change-status';

//manager user
$('.btn-modal-app').on('click', function(){
    updateTitle('Thêm mới');
});
function deleteWithAjax(id){
    let data = {id : id}
    callAjax(urlDelete, 'DELETE', data);
}
function editWithAjax(id){
    let data = {id : id}
    callAjax(urlEdit, 'GET', data);
    $('#myModal').modal('show');
    updateTitle('Cập nhật');
}
function updateTitle(mode){
    $('.mode').text(mode);
}

function callAjax(url, method, data) {
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
$.fn.changeStatus = function(id){
    let data = {
            id : id,
            statusChanged : $(this).prop("checked")
        }
    callAjax(urlChangeStatus, 'GET', data);
    myToastr('success',  'Cập nhật thành công!');
}
function callAjaxPromise(url, method, data) {
    return new Promise((resolve, reject) => {
      $.ajax({
        url: url,
        method: method,
        data: data
      }).then(function(response) {
        resolve(response);
      }).fail(function(error) {
        console.log("error : " + error);
        reject(error);
      });
    });
}
