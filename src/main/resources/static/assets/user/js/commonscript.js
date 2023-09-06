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
/*if (line) {
    line.style.left = tabActive.offsetLeft + "px";
    line.style.width = tabActive.offsetWidth + "px";
}*/


tabs.forEach((tab, index) => {
    const pane = panes[index];
    tab.onclick = function() {
        document.querySelector(".tab-item.active").classList.remove("active");
        document.querySelector(".tab-pane.active").classList.remove("active");

        /*line.style.left = this.offsetLeft + "px";
        line.style.width = this.offsetWidth + "px";*/

        this.classList.add("active");
        pane.classList.add("active");
    };
});
//resize and load to responsive header__nav
/*window.addEventListener("load", function() {
  var widthWindow = window.innerWidth;
    if (widthWindow <= 1024) {
     $('.sub-nav-wrap-responsive').removeClass('sub-nav-wrap');
     $('.sub-nav__item.sub-1').on('click', (event) => {
         event.preventDefault();
         $(event.currentTarget).toggleClass("active");
         $(event.currentTarget).next('.wrapper__sub-2').toggleClass("show");
         $(event.currentTarget).find('i').toggleClass("show");
         $(event.currentTarget).find('i').toggleClass("fa-angle-up");
     });
    }else{
      $('.sub-nav-wrap-responsive').addClass('sub-nav-wrap');
    }
});*/
/*window.addEventListener("resize", function() {
  var widthWindow = window.innerWidth;
  if (widthWindow <= 1024) {
   $('.sub-nav-wrap-responsive').removeClass('sub-nav-wrap');

  }else{
    $('.sub-nav-wrap-responsive').addClass('sub-nav-wrap');
  }
});*/

$('.nav__link-accordion').on('click', (event) => {
       event.preventDefault();
       $(event.currentTarget).next('.sub-nav-wrap-responsive').toggleClass("active");
       $(event.currentTarget).find('i').toggleClass("fa-angle-down");
       $(event.currentTarget).find('i').toggleClass("fa-angle-up");
});
$('.sub-nav__item.sub-1').on('click', (event) => {
       event.preventDefault();
       $(event.currentTarget).toggleClass("active");
       $(event.currentTarget).next('.wrapper__sub-2').toggleClass("show");
       $(event.currentTarget).find('i').toggleClass("fa-angle-down");
       $(event.currentTarget).find('i').toggleClass("fa-angle-up");
});



//url
var urlInCreaseCart =   '/cart/update?action=increase';
var urlDecreaseCart =   '/cart/update?action=decrease';
var urlDeleteCart   =   '/cart/delete';
var urlAddCart      =   '/cart/add';
var urlListProduct  =   '/list-product';
var urlLogin        =   '/login-with-ajax';
var urlSignup       =   '/signup-with-ajax';
var urlRare         =   '/rate';
var urlSendCode     =   '/forgot-password/send-code';
var urlForgotPass   =   '/forgot-password';
var urlFeedback   =   '/feedback';

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
                window.location.href = "/"
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
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    let fullName = $('#name_signup').val();
    let email = $('#email_signup').val();
    let password = $('#password_signup').val();
    let re_password = $('#re_password_signup').val();

    $(".error_email_signup").hide();
    $(".msg_pass_not_match").hide();

    if(!emailPattern.test(email)){
        $(".error_email_signup").text('Định dạng email không hợp lệ!');
        $(".error_email_signup").show();
        return;
    } else if(password !== re_password){
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
              /*  $("#my-Register").hide();*/
                window.location.hash = "my-Login";
             }else if(response === 'ALREADY EXIST'){
                $(".error_email_signup").text('Tài khoản đã tồn tại!');
                $(".error_email_signup").show();
             }
        }).fail(function(error) {
          console.log("error : " + error);
        });
    }
});


// cart func
async function addToCart(name, price, mainImageProduct){
      try {
        let data = {nameProduct : name, priceProduct : price, mainImageProduct : mainImageProduct}
        let listCartItems = await callAjaxCartPromise(urlAddCart, 'POST', data) || [];

        updateHtmlAfterAddCart(listCartItems);
      } catch (error) {
        window.location.hash = "my-Login";
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

//ajax func
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

/* forgot password*/
$('#btnSendCode').on('click', async ()=>{
    let email = $('#email_forgot').val();

    let response = await callAjaxCartPromise(urlSendCode,'POST', {email : email});
    if(response === 'OK'){
        $('.msg_not_exist').hide();
        $('.msg_sent_code').show();
    }else if(response === 'NOT FOUND'){
        $('.msg_sent_code').hide();
        $('.msg_not_exist').show();
    }
});

$('#btn_forgotSubmit').on('click', async ()=>{
    let email                = $('#email_forgot').val();
    let code_confirm         = $('#code_confirm').val();
    let new_password         = $('#new_password').val();
    let re_password_forgot   = $('#re_password_forgot').val();

    if(new_password !== re_password_forgot){
        $('.msg_pass_not_match').show();
    }else{
        $('.msg_pass_not_match').hide();
    }
    let data = {
        email   : email,
        code    : code_confirm,
        newPass : new_password
    }
    let response = await callAjaxCartPromise(urlForgotPass,'POST', data);

    if(response === 'OK'){
        window.location.hash = "my-Login";
    }else if(response === 'NOT MATCH'){
        $('.msg_sent_code').text('Mã xác nhận không khớp!');
        $('.msg_sent_code').show();
    }
})
/* end forgot password*/

// Rateting js
$('#btnSendRate').on('click', ()=>{
    let rate_content = $('#rate_content').val();
    let productId = $('#productId').val();
    let numStar = $("input[name='rating']:checked").val();

    if(rate_content === ""){
        $('#rate_content').focus();
        $('.msg__rare-warning').show()
        return false;
    }else{
        let data = {
             numStar : parseInt(numStar),
             content : rate_content,
             productId : parseInt(productId)
         }
        $.ajax({
              url: urlRare,
              method: 'POST',
              contentType:"application/json; charset=utf-8",
              data: JSON.stringify(data),
        }).then(function(response) {
              if(response === 'OK'){
                 $('.msg__rare-warning').hide();
                 addRateHtml(rate_content, numStar);
                 $('#rate_content').val('');
              }else if(response === 'UN_AUTHORIZATION'){
                  window.location.hash = "my-Login";
                 return;
              }
        }).fail(function(error) {
              alert("error : " + error);
        });

    }

});

/*contact page*/
$('#sendContact').on('click', ()=>{
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    let name = $('#name_contact').val();
    let email = $('#email_contact').val();
    let address = $('#address_contact').val();
    let phone = $('#sdt_contact').val();
    let content = $('#content_contact').val();

    $(".email_not_valid").hide();

    if(!emailPattern.test(email)){
        $(".email_not_valid").show();
        return;
    }else{
        let dataToSend =  {
                              name : name,
                              email : email,
                              address : address,
                              phoneNumber : phone,
                              content : content
                          }
         $.ajax({
              url: urlFeedback,
              method: 'POST',
              contentType:"application/json; charset=utf-8",
              data : JSON.stringify(dataToSend),
              dataType: "text",
        }).then(function(response) {
            SwalAlertSuccess("Gửi thành công!");
        }).fail(function(error) {
          console.log("error : " + error);
        });
    }
})

// update html Evaluate
function addRateHtml(content, numStar){
    let nameUser = $('#nameUser').val();
    var starHTML = '';
    for (var i = 0; i < numStar; i++) {
      starHTML += '<i class="fas fa-star"></i>';
    }
    let html = `
        <li class="rate__item">
            <div class="rate__info">
                <img src="/assets/default_user.png" alt="img">
                <h3 class="rate__user">${nameUser}</h3>
                <div class="rate__star">
                    <div class="group-star">
                         ${starHTML}
                    </div>
                </div>
            </div>
            <div class="rate__comment">${content}</div>
        </li>
    `;
    $('.rate__list').append(html)
}

// update html cart
function updateHtmlAfterAddCart(listCart){
    if(listCart != null){
        let html = '';
             listCart.forEach( cartItem => {
             let nameProd = cartItem.nameProduct.replace("'", "\\'");
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
                            <a onclick="deleteCart('${nameProd}')" class="order-close pointer"><i class="far fa-times-circle"></i></a>
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

