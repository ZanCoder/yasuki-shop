
function myOnLoading(){
    $('.preloader').show();
}
var urlDelete = window.location.pathname + '/delete';
var urlEdit = window.location.pathname + '/edit';
var urlSearch = window.location.pathname;
var urlChangeStatus = window.location.pathname + '/change-status';
var urlChangeGC = '/admin/manager-product/change-group-category';
var urlSearchProdToOrder = '/admin/manager-order/find-by-keyword';
var urlFindByGroup = '/admin/manager-order/find-by-group';
var urlFindByCategory = '/admin/manager-order/find-by-category';
var urlManagerOrder = '/admin/manager-order';
var urlAddOrder = '/admin/manager-order/add';

//delete order
function deleteOrder(id){
    alertify.dialog('confirm').set({
        transition:'zoom',
        title: 'Xác nhận xóa',
        message: 'Bạn có muốn xóa đơn hàng này không ?',
        'onok': function(){
            deleteWithAjax(id);
        }
    }).show();
}

//delete category (confirm)
function deleteCategory(id){
    alertify.dialog('confirm').set({
        transition:'zoom',
        title: 'Xác nhận xóa',
        message: 'Tất cả sản phẩm thuộc danh mục này đều sẽ bị xóa, bạn có muốn xóa không ?',
        'onok': function(){
            deleteWithAjax(id);
        }
    }).show();
}


//delete group category (confirm)
function deleteGroupCategory(id){
    alertify.dialog('confirm').set({
        transition:'zoom',
        title: 'Xác nhận xóa',
        message: 'Tất cả danh mục thuộc nhóm danh mục này đều sẽ bị xóa, bạn có muốn xóa không ?',
        'onok': function(){
            deleteWithAjax(id);
        }
    }).show();
}

/*add product to order*/
$('.btnAddProductToOrder').on('click', ()=>{
    event.preventDefault();
    filterByGroupCategory();
})
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


function addProductToOrder(productId, name, price){
    let item = {
        quantity : 1,
        productId : productId,
        name : name,
        price : price,
    }
    let listProductSelected = localStorage.getItem('listProductSelected') || [];
    if(listProductSelected.length > 0){ // have data in localStorage
        listProductSelected = JSON.parse(listProductSelected);
        let foundItem = listProductSelected.find( iz =>  parseInt(iz.productId) === parseInt(productId));
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
function deleteProdInListSelected(productId){
     let listProductSelected = localStorage.getItem('listProductSelected');
     listProductSelected = JSON.parse(listProductSelected);
     const newArray = listProductSelected.filter(item => parseInt(item.productId) !== parseInt(productId));
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
    if(dataOrder == null || dataOrder.length < 2){ // []
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
            status:     $('#status_order').val(),
            idOrder :   $('#id_order_edit').val(),
            cartDtoList : JSON.parse(dataOrder)
    }
    if(dataOrder !== null && dataOrder.length > 2){
        $.ajax({
            url : urlAddOrder,
            method : 'POST',
            contentType: "application/json",
            dataType: "text",
            data : JSON.stringify(dataToSend)
        }).then(function(data){
            if(data == 'OK'){
                window.location.href = urlManagerOrder;
            }
        }).fail(function(error){
            console.log('error' + error)
        })
    }else{
        console.log('null');
    }
    localStorage.removeItem('listProductSelected');
});

//update html list product search to order
function updateHtmlListProdSearch(listProds){
    $('#list_prod_order').html('')
    listProds.forEach( item =>{
        let nameProd = item.name.replace("'", "\\'");
        $('#list_prod_order').append(
        `
            <tr>
                <td>${item.name}</td>
                <td>${formatDecimal(item.price)}</td>
                <td>
                    <button onclick="addProductToOrder('${item.id}', '${nameProd}', '${item.price}')"
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
                            <button onclick="deleteProdInListSelected('${item.productId}')"
                            class="btn btn-danger" type="button">Xóa</button>
                        </td>
                    </tr>
                `
                );
            });
    }
}


// show price after discount (just for see)
$("#price_prod").on("blur", function () {
    showPriceAfterDiscount();
});
$("#percent_discount").on("blur", function () {
    showPriceAfterDiscount();
});

function showPriceAfterDiscount() {
    var price = parseFloat($('#price_prod').val());
    var percentDiscount = parseFloat($('#percent_discount').val());
    var discountedPrice = price * (1 - percentDiscount / 100);
    var roundedValue = roundToNearestThousand(discountedPrice);
    $('#price_after_discount').val(roundedValue.toString());
}

function roundToNearestThousand(value) {
    return Math.ceil(value / 1000) * 1000;
}


//toastr
function myToastr(type, message){
 var delay = alertify.get('notifier', 'delay', 1);
     alertify.set('notifier','position', 'top-right');
     if(type == 'error'){
        alertify.error(message);
     }else{
        alertify.success(message);
     }
     alertify.set('notifier','delay', delay);
}

function formatDecimal(num){
    return numbro(num).format({thousandSeparated: true});
}
