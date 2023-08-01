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

//url
var domain = '/yasuki';
var urlInCreaseCart =   domain + '/cart/update?action=increase';
var urlDecreaseCart =   domain + '/cart/update?action=decrease';
var urlDeleteCart   =   domain + '/cart/delete';
var urlAddCart      =   domain + '/cart/add';
var urlListProduct  =   domain + '/list-product';
var urlLogin        =   domain + '/login-with-ajax';
var urlSignup      =   domain + '/signup-with-ajax';

//login
$('#submit_modal_login').on('click', ()=>{
    let username = $('#email_modal_login').val();
    let password = $('#pass_modal_login').val();

    $(".account_not_found_message").hide();
    $(".invalid_password_message").hide();
    $.ajax({
          url: urlLogin,
          method: 'POST',
          data: {username : username, password : password}
        }).then(function(response) {

             if(response === 'OK'){
                window.location.href = domain;
             }else if(response === 'NOT FOUND'){
                $(".account_not_found_message").show();
             }else if(response === 'INVALID'){
                $(".invalid_password_message").show();
             }
        }).fail(function(error) {
          console.log("error : " + error);
        });

});


//sign up
$('#btn_signup').on('click', ()=>{
    let fullName = $('#name_signup').val();
    let email = $('#email_signup').val();
    let password = $('#password_signup').val();
    let re_password = $('#re_password_signup').val();

    $(".msg_already_exist").hide();
    $(".msg_pass_not_match").hide();

    if(password !== re_password){
        $(".msg_pass_not_match").show();
        return;
    }else{
        let dataToSend =  {
                              email : email,
                              password : password,
                              fullName : fullName
                          }
         $.ajax({
              url: urlSignup,
              method: 'POST',
              contentType:"application/json; charset=utf-8",
              data : JSON.stringify(dataToSend),
              dataType: "text",
        }).then(function(response) {
             if(response === 'OK'){
                $("#my-Register").hide();
                $("#my-Login").show();
             }else if(response === 'ALREADY EXIST'){
                $(".msg_already_exist").show();
             }
        }).fail(function(error) {
          console.log("error : " + error);
        });
    }
});


// cart
async function addToCart(name, price, mainImageProduct){
      try {
        let data = {nameProduct : name, priceProduct : price, mainImageProduct : mainImageProduct}
        let listCartItems = await callAjaxCartPromise(urlAddCart, 'POST', data) || [];

        updateHtmlAfterAddCart(listCartItems);
      } catch (error) {
        $("#my-Login").show();
        return;
      }
      SwalAlertSuccess("Thêm thành công!");
}

async function deleteCart(nameProduct){
    let data =  {nameProduct : nameProduct}
    await callAjaxCartPromise(urlDeleteCart,'DELETE', data);
    window.location.reload();
}

async  function plusProduct(nameProduct, cartIndex){
    let data = {nameProduct : nameProduct};
    await  callAjaxCartPromise(urlInCreaseCart, 'POST', data);
    window.location.reload();
 }
async  function minusProduct(nameProduct, cartIndex){
    var currentVal = parseInt($('#'+ cartIndex).val());
    if(currentVal <= 1) return;
    let data =  {nameProduct : nameProduct}
    await  callAjaxCartPromise(urlDecreaseCart, 'POST', data);
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
    if(listCart != null){
        let html = '';
             listCart.forEach( cartItem => {
             let formattedPrice = /*[[${#numbers.formatDecimal(cartItem.priceProduct, 3, 'POINT', 0, 'COMMA')}]]*/ '';
             html +=`
                    <li  class="item-order">
                        <div class="order-wrap">
                            <a href="#" class="order-img">
                                <img src='${cartItem.mainImageProduct}'alt="error">
                            </a>
                            <div class="order-main">
                                <a href="#" class="order-main-name">${cartItem.nameProduct}</a>
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
}
//common function
function formatDecimal(num){
    return numbro(num).format({thousandSeparated: true});
}
function isValidEmail(email) {
  const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  return emailRegex.test(email);
}

// Swal alert
function SwalAlertSuccess(message){
    Swal.fire({
        title: message,
        icon: "success",
        showConfirmButton: false,
        timer : 2000
    });

}

function SwalAlertWarning(message){
    Swal.fire({
        title: message,
        icon: 'warning',
        confirmButtonText: 'Đóng',
        timer: 2000,
    });
}

function SwalAlertOrderSuccess(message){
    Swal.fire({
        title: message,
        icon: "success",

        confirmButtonText: "Tiếp tục mua hàng!"
    }).then((result) => {
        if (result.isConfirmed) {
             window.location.href = urlListProduct;
        }
    });
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

