//$('#btnAddProd').on('click', () =>{
//    $('#list_prod_order').append(`
//        <tr class="even">
//            <td class="dtr-control" tabindex="0">1</td>
//            <td class="">All others</td>
//            <td class="sorting_1">999</td>
//            <td class="sorting_1">999</td>
//            <td>
//            <button class="btn btn-danger btnDelAccount">
//                <i class="far fa-trash-alt"></i>
//            </button>
//            </td>
//        </tr>
//    `)
//});

function myOnLoading(){
    $('.preloader').show();
}
var urlDelete = window.location.pathname + '/delete';
var urlEdit = window.location.pathname + '/edit';
var urlSearch = window.location.pathname;
var urlChangeStatus = window.location.pathname + '/change-status';
var urlChangeGC = '/yasuki/admin/manager-product/change-group-category';
var urlSearchProdToOrder = '/yasuki/admin/manager-order/find-by-keyword';
var urlFindByGroup = '/yasuki/admin/manager-order/find-by-group';
var urlFindByCategory = '/yasuki/admin/manager-order/find-by-category';
var urlManagerOrder = '/yasuki/admin/manager-order';


//manager user
$('.btn-modal-app').on('click', function(){
    updateTitle('Thêm mới');
});
//global func with ajax
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
$.fn.changeStatus = function(id){
    let data = {
            id : id,
            statusChanged : $(this).prop("checked")
        }
    callAjax(urlChangeStatus, 'GET', data);
    myToastr('success',  'Cập nhật thành công!');
}

function updateDataField(data){
    for (var field in data) {
      if (data.hasOwnProperty(field)) {
        var value = data[field];
        $('[data-field="' + field + '"]').val(value);
      }
    }
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

// change data list select when change
$('#name_gr_category').on('change',async function(){
       let data = { id : $('#name_gr_category').val() }
       let listCateGroupItems = await callAjaxPromise(urlChangeGC, 'GET', data) || [];
       updateCategoriesSelect(listCateGroupItems);
});

function updateCategoriesSelect(listCateGroupItems){
    $('#name_category').html('')
    listCateGroupItems.forEach( item => {
        $('#name_category').append(`
            <option value='${item.id}' >${item.name}</option>
        `);
    });
}

//search product to order
async function searchProduct(){
    let keyword = $('#inputSearchProd').val();
    let data = {keyword : keyword}
    let listProds = await callAjaxPromise(urlSearchProdToOrder, 'GET', data) || [];
    updateHtmlListProdSearch(listProds);
}

async function filterByGroupCategory(){
    let id = $('#name_gr_category').val();
    let data = {id : id}
    let listProds = await callAjaxPromise(urlFindByGroup, 'GET', data) || [];
    updateHtmlListProdSearch(listProds);
}
async function filterByCategory(){
    let id = $('#name_category').val();
    let data = {id : id}
    let listProds = await callAjaxPromise(urlFindByCategory, 'GET', data) || [];
    updateHtmlListProdSearch(listProds);
}


function addProductToOrder(name, price){
    let item = {
        quantity : 1,
        name : name,
        price : price,
    }
    let listProductSelected = localStorage.getItem('listProductSelected') || [];
    if(listProductSelected.length > 0){ // have data in localStorage
        listProductSelected = JSON.parse(listProductSelected);
        let foundItem = listProductSelected.find( iz => iz.name === name);
        if (foundItem !== undefined ) { // !== undefined )
            foundItem.quantity += 1;
        }else{
            listProductSelected.push(item);
        }
    }else{
        listProductSelected.push(item);
    }
    localStorage.setItem('listProductSelected', JSON.stringify(listProductSelected));
    updateHtmlListProdSelected(listProductSelected);
}
function deleteProdInListSelected(name){
     let listProductSelected = localStorage.getItem('listProductSelected');
     listProductSelected = JSON.parse(listProductSelected);
     const newArray = listProductSelected.filter(item => item.name !== name);
     localStorage.setItem('listProductSelected', JSON.stringify(newArray));
     updateHtmlListProdSelected(newArray);
}
function resetInfoOrder(){
    localStorage.removeItem('listProductSelected');
    $('#list_prod_selected_to_order').html('');
}


<!--    send order -->
$('#sendOrder').on('click', function sendOrder(){
    var dataOrder = localStorage.getItem('listProductSelected');
    if(dataOrder == null){
        alert('Vui lòng chọn sản phẩm!');
        return;
    }

    const urlParams = new URLSearchParams(window.location.href);

    var dataToSend = {
            name:       $('#name_order').val(),
            address:    $('#address_order').val(),
            email:      $('#email_order').val(),
            phoneNumber:$('#phone_number').val(),
            note:       $('#note_order').val(),
            idOrder :   $('#id_order_edit').val(),
            cartDtoList : JSON.parse(dataOrder)
    }
    if(dataOrder !== null){
        $.ajax({
            url : '/yasuki/order',
            method : 'POST',
            contentType: "application/json",
            dataType: "text",
            data : JSON.stringify(dataToSend)
        }).then(function(data){
            if(data == 'OK'){
                alert('success')
                window.location.href = urlManagerOrder;
            }
        }).fail(function(error){
            alert('error' + error)
        })
    }else{
        alert('null');
    }
    localStorage.removeItem('listProductSelected');
});



//update html list product search to order
function updateHtmlListProdSearch(listProds){
    $('#list_prod_order').html('')
    listProds.forEach( item =>{
        $('#list_prod_order').append(
        `
            <tr>
                <td>${item.name}</td>
                <td>${item.quantityLeft}</td>
                <td>${formatDecimal(item.price)}</td>
                <td>
                    <button onclick="addProductToOrder('${item.name}', '${item.price}')"
                    class="btn btn-primary btnSelectProd" type="button">Thêm</button>
                </td>
            </tr>
        `
        );
    })
}



//update html list product selected to order
function updateHtmlListProdSelected(listProds){
    if(listProds != null){
        $('#list_prod_selected_to_order').html('');
            listProds.forEach( (item, index) =>{
                $('#list_prod_selected_to_order').append(
                `
                    <tr>
                        <td>${index+1}</td>
                        <td>${item.name}</td>
                        <td>
                           ${item.quantity}
                        </td>
                        <td>${formatDecimal(item.price)}</td>
                        <td>
                            <button onclick="deleteProdInListSelected('${item.name}')"
                            class="btn btn-danger" type="button">Xóa</button>
                        </td>
                    </tr>
                `
                );
            });
    }
}

//toastr
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

function formatDecimal(num){
    return numbro(num).format({thousandSeparated: true});
}
