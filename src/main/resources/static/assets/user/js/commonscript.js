// Btn show navbar
var menuBtn = document.querySelector('.navbar-icon')
var myMenu = document.querySelector('.header__nav')
menuBtn.onclick = function() {
    myMenu.classList.toggle('show')
    menuBtn.classList.toggle('open')
}

// Go to Top
function goToTop() {
    var timer = setInterval(function() {
        document.documentElement.scrollTop -= 20
        if (document.documentElement.scrollTop <= 0) {
            clearInterval(timer)
        }
    }, 10)
}

// Tabline
const tabs = document.querySelectorAll(".tab-item");
const panes = document.querySelectorAll(".tab-pane");

const tabActive = document.querySelector(".tab-item.active");
const line = document.querySelector(".tabs .line");
if (line) {
    line.style.left = tabActive.offsetLeft + "px";
    line.style.width = tabActive.offsetWidth + "px";
}


tabs.forEach((tab, index) => {
    const pane = panes[index];
    tab.onclick = function() {
        document.querySelector(".tab-item.active").classList.remove("active");
        document.querySelector(".tab-pane.active").classList.remove("active");

        line.style.left = this.offsetLeft + "px";
        line.style.width = this.offsetWidth + "px";

        this.classList.add("active");
        pane.classList.add("active");
    };
});

function toastSuccess(message){
    Command: toastr["success"](message)

    toastr.options = {
      "closeButton": false,
      "debug": false,
      "newestOnTop": false,
      "progressBar": false,
      "positionClass": "toast-top-right",
      "preventDuplicates": false,
      "onclick": null,
      "showDuration": "300",
      "hideDuration": "1000",
      "timeOut": "3000",
      "extendedTimeOut": "1000",
      "showEasing": "swing",
      "hideEasing": "linear",
      "showMethod": "fadeIn",
      "hideMethod": "fadeOut"
    }
}

// cart
var urlInCreaseCart = '/yasuki/cart/update?action=increase';
var urlDecreaseCart = '/yasuki/cart/update?action=decrease';
var urlDeleteCart = '/yasuki/cart/delete';
var urlAddCart = '/yasuki/cart/add';

async function addToCart(name, price){
      try {
        let data = {nameProduct : name, priceProduct : price}
        let listCartItems = await callAjaxCartPromise(urlAddCart, 'POST', data) || [];
        toastSuccess("Thêm thành công!");
        updateHtmlAfterAddCart(listCartItems);
      } catch (error) {
        console.error("Error:", error);
      }
}

async function deleteCart(nameProduct){
    let data =  {nameProduct : nameProduct}
    await callAjaxCartPromise(urlDeleteCart,'DELETE', data);
    toastSuccess("Đã xóa!");
    window.location.reload();
}

async  function plusProduct(nameProduct, cartIndex){
    let data = {nameProduct : nameProduct};
    await  callAjaxCartPromise(urlInCreaseCart, 'POST', data);
    toastSuccess("Đã cập nhật!");
    window.location.reload();
 }
 async  function minusProduct(nameProduct, cartIndex){
     var currentVal = parseInt($('#'+ cartIndex).val());
     if(currentVal <= 1) return;
     let data =  {nameProduct : nameProduct}
     await  callAjaxCartPromise(urlDecreaseCart, 'POST', data);
     toastSuccess("Đã cập nhật!");
     window.location.reload();
 }

 function callAjaxCart(url, method, data) {
    $.ajax({
      url: url,
      method: method,
      data: data
    }).then(function(response) {

    }).fail(function(error) {
      console.log("error : " + error);
    });
}

function callAjaxCartPromise(url, method, data) {
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

function updateHtmlAfterAddCart(listCart){
     let html = '';
     listCart.forEach( cartItem => {
     let formattedPrice = /*[[${#numbers.formatDecimal(cartItem.priceProduct, 3, 'POINT', 0, 'COMMA')}]]*/ '';
     html +=`
            <li  class="item-order">
                <div class="order-wrap">
                    <a href="product.html" class="order-img">
                        <img src="./assets/img/product/product1.jpg" alt="">
                    </a>
                    <div class="order-main">
                        <a href="product.html" class="order-main-name">${cartItem.nameProduct}</a>
                        <div class="order-main-price">${cartItem.quantity} x ${formatDecimal(cartItem.priceProduct)} ₫</div>
                    </div>
                    <a onclick="deleteCart('${cartItem.nameProduct}')" class="order-close"><i class="far fa-times-circle"></i></a>
                </div>
            </li>
        `
     })
    $('.order__list').html(html);
    $('.header__cart-amount').text(listCart.length);
}

function formatDecimal(num){
    return numbro(num).format({thousandSeparated: true});
}





// // // Rateting js
// // $(':radio').change(function() {
// //     console.log('New star rating: ' + this.value);
// // });
// var value = parseInt(document.querySelector('.input-qty').value, 10);
// var maxProduct = document.querySelector('.input-qty').getAttribute('max')

// function minusProduct() {
//     if (value > 0) {
//         value = isNaN(value) ? 1 : value;
//         value--;
//     }

//     document.querySelector('.input-qty').value = value;
// }

// function plusProduct() {
//     value = isNaN(value) ? 0 : value;
//     value++;
//     if (value > maxProduct) {
//         value = maxProduct;
//         alert('Số sản phẩm trong kho của shop đã đạt  giới hạn')
//     }
//     document.querySelector('.input-qty').value = value;
// }

