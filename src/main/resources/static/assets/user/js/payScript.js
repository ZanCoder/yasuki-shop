"use strict";
    $(document).ready(function() {
<!--    show data order -->
        var dataJson = localStorage.getItem('dataOrder') || [];
        if (dataJson) {
          var data = JSON.parse(dataJson);
          let html = data.map((item) => {
              return `
                        <div class="pay-info">
                            <div class="main__pay-text"> ${item.nameProduct} </div>
                            <div class="main__pay-amount">
                                ${item.quantity}
                            </div>
                            <div class="main__pay-price">
                                ${formatDecimal(item.priceProduct * item.quantity)} ₫
                            </div>
                        </div>
              `
          }).join('');
          $('.display-data-order').html(html);
          let total = formatDecimal(totalPayment(data));
          $('.totalOrder').text(total);
        };

<!--    send order -->
        $('#sendOrder').on('click', function(){
            var dataOrder = localStorage.getItem('dataOrder');
            var dataToSend = {
                name:       $('#name_order').val(),
                address:    $('#address_order').val(),
                email:      $('#email_order').val(),
                phoneNumber:$('#phoneNumber_order').val(),
                note:       $('#note_order').val(),
                cartDtoList : JSON.parse(dataOrder)
            }
            if(dataOrder !== null){
                SwalAlertOrderSuccess('Đặt hàng thành công!');
                $.ajax({
                    url : '/order',
                    method : 'POST',
                    contentType: "application/json",
                    dataType: "text",
                    data : JSON.stringify(dataToSend)
                }).then(function(data){

                    if(data == 'OK'){
                        localStorage.removeItem('dataOrder');
                    }
                }).fail(function(error){
                    alert('error' + error)
                })
            }else{
                alert('null')
            }

        });

        function totalPayment(data){
              return data.reduce((total, item) => {
                   return total + item.priceProduct * item.quantity;
              },0);
        }
  });