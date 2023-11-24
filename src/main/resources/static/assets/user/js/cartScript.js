$(window).on('load', function() {
    $('input[name=chkCartItem]').each(function(index, item) {
        $(item).prop('checked', false);
    });
    localStorage.removeItem('listCartItemSelected');
    localStorage.removeItem('totalPay');
});
async function deleteCart(productId){
    updateTotalPay('delete' ,productId);
    let data  = { productId: productId}
    let amountCart = await callAjaxCartPromise(urlDeleteCart,'DELETE', data);
    $('#cartItem'+ productId).remove();
    updateHtmlAfterAddCart(amountCart);
}

async function updateCart(action ,productId){
    let cartItem = $('#cart'+productId);
    if(action === 'decrease'){
        if(cartItem.val() == 1) return;
        cartItem.val( parseInt(cartItem.val()) - 1 );
    }else{
        cartItem.val( parseInt(cartItem.val()) + 1 );
    }
    let data  = {
        action: action,
        productId: productId
    }
    let amountCart = await callAjaxCartPromise(urlUpdateCart, 'POST', data);
    updateHtmlAfterAddCart(amountCart);
    localStorage.removeItem('listCartItemSelected');
    localStorage.removeItem('totalPay');
    window.location.reload();
}

function updateTotalPay(action, productId) {
    let listInput = $('input[name=chkCartItem]');
    let priceProduct = 0;
    listInput.each(function(index, item) {
        if ($(item).data('product-id') === productId && $(item).prop('checked')) {
            priceProduct = $(item).data('product-price');
            let totalPay = parseInt(localStorage.getItem('totalPay')) || 0;

            if (action === 'decrease') {
                totalPay -= parseInt(priceProduct);
            }else if (action === 'delete') {
                let quantity = $('#cart'+productId).val();
                totalPay -= (parseInt(priceProduct)*quantity);
                let currentItemSelected = JSON.parse(localStorage.getItem('listCartItemSelected')) || [];
                currentItemSelected = currentItemSelected.filter(item => item.productId !== productId);
                localStorage.setItem('listCartItemSelected', JSON.stringify(currentItemSelected));
            }else {
                totalPay += parseInt(priceProduct);
            }
            $('.totalCart').html(formatDecimal(totalPay > 0 ? totalPay : 0));
            localStorage.setItem('totalPay', JSON.stringify(totalPay));
        }
    });
}

$('input[name=chkCartItem]').on('click', function() {
    let productId = $(this).data('product-id');
    let productPrice = $(this).data('product-price');
    let quantity = $('#cart'+productId).val();

    // Lấy danh sách mặt hàng đã chọn từ localStorage
    let currentItemSelected = JSON.parse(localStorage.getItem('listCartItemSelected')) || [];
    let totalPay = parseInt(JSON.parse(localStorage.getItem('totalPay'))) || 0;
    if ($(this).prop('checked')) {
        // push cart item into localStorage
        let cartDtoItem = {
            productId: productId,
            quantity: quantity
        };
        currentItemSelected.push(cartDtoItem);
        // update total pay
        totalPay += (productPrice*quantity);
    }else{
        if(totalPay != 0){
            currentItemSelected = currentItemSelected.filter(item => item.productId !== productId);
            totalPay -= (productPrice*quantity);
        }
    }
    // Lưu danh sách cập nhật vào localStorage
    localStorage.setItem('listCartItemSelected', JSON.stringify(currentItemSelected));
    localStorage.setItem('totalPay', JSON.stringify(totalPay));
    $('.totalCart').html(formatDecimal(totalPay > 0 ? totalPay : 0));
    $('.type_order').html(totalPay > 500000 ? 'Giao hàng miễn phí' : 'Người nhận trả ship');
});

$('#order').on('click', function(){
    let currentItemSelected = JSON.parse(localStorage.getItem('listCartItemSelected')) || [];
    let totalPay = parseInt(localStorage.getItem('totalPay')) || 0;
    try{
        parseInt(totalPay);
    }catch(error){
         event.preventDefault();
    }
    if(currentItemSelected.length !== 0){
        window.location.href = "/order"
    }else{
        event.preventDefault();
        SwalAlertWarning('Bạn chưa chọn sản phẩm nào!');
    }
});
function updateHtmlAfterAddCart(sizeCart){
    $('.header__cart-amount').text(sizeCart ? sizeCart : '0')
}