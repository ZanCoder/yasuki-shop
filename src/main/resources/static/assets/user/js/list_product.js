var urlGetProductDetail = '/detail-product'
var urlCompare = '/product/data-compare'
var urlComparePage = '/product/compare'
$('.popup__compare').on('click', ()=>{
    $('.popup__compare').hide();
    $('.stick-compare').show();
});

$('.collapse__list-compare').on('click', ()=>{
    $('.popup__compare').show();
    $('.stick-compare').hide();
});
async function toggleCompareItem(buttonElement, productId, event){
    let currentList = JSON.parse(localStorage.getItem('listCompare')) || [];
    let checkExist = currentList.some( item => item.id === productId);
    if(checkExist){
        deleteItemCompare(productId);
        updateTitleCompare('delete', buttonElement);
        return;
    }

    try {
        let response = await $.ajax({
            url: urlGetProductDetail,
            method: 'GET',
            data: { productId: productId }
        });

        if(response != null){
            let itemCompare = {
                id :    response.id,
                image :   response.mainImage,
                name :  response.name
            }
            if(currentList.length == 0 || currentList.length <3){
                currentList.push(itemCompare);
                localStorage.setItem('listCompare', JSON.stringify(currentList));
                updateHtmlCompare(currentList);
                $('.stick-compare').show();
                $('.popup__compare').hide();
                updateTitleCompare('add', buttonElement);
            }else{
                return;
            }
        }
    } catch (error) {
        console.error("error : " + error);
    }
}

function updateHtmlCompare(dataCompare){
    if(dataCompare.length == 0 ){
         $('.stick-compare').hide();
         $('.popup__compare').hide();
    }
    let listItemCompare = $('.wrapper__item-compare');

    dataCompare.forEach( (item, index) => {
        listItemCompare.eq(index).empty()
        listItemCompare.eq(index).append(`
                <div class="item__data-compare">
                    <img class="img__item-compare" src="${item.image}" alt="img">
                    <div class="name__item-compare">${item.name}</div>
                    <button class="btn del__item-compare" onclick="triggerBtnCompare(${item.id})">
                        <i class="far fa-times-circle"></i>
                    </button>
                </div>
        `);
    });
    for(let i = dataCompare.length; i <= 3; i ++){
        listItemCompare.eq(i).empty()
        listItemCompare.eq(i).append(`
             <div class="item__plus-compare">
                <div class="icon__plus-compare">
                    <i class="fas fa-plus"></i>
                </div>
                <div class="title__add-compare">Thêm sản phẩm</div>
            </div>
        `);
    }
    $('.total__item-compare').text(dataCompare.length);
    if(dataCompare.length < 2){
         $('#compareProduct').prop('disabled', true);
    }else{
        $('#compareProduct').prop('disabled', false);
    }
}
function deleteItemCompare(productId){
    let currentList = JSON.parse(localStorage.getItem('listCompare')) || [];
    let newList = currentList.filter( item => item.id != productId);
    localStorage.setItem('listCompare',JSON.stringify(newList));
    updateHtmlCompare(newList);
}

$('.txtremoveall').on('click', ()=>{
    let currentList = JSON.parse(localStorage.getItem('listCompare')) || [];
    currentList.forEach(item =>{
        triggerBtnCompare(item.id);
    });
    localStorage.removeItem('listCompare');
});

function updateTitleCompare(type, buttonElement){
    if(type === 'delete'){
        $(buttonElement).removeClass('active')
    }else if(type === 'add'){
        $(buttonElement).addClass('active')
    }
}
function triggerBtnCompare(productId) {
        $('.compare__item').each(function(index, item) {
            if ($(this).data('product-id') == productId) {
                $(this).trigger('click');
            }
        });
        deleteItemCompare(productId);
}

$('#compareProduct').on('click', ()=>{
        let dataList = JSON.parse(localStorage.getItem('listCompare')) || [];
        let listProductId = dataList.map( item => item.id)
        $.ajax({
           url: urlCompare,
           method: 'POST',
           contentType : "application/json; charset=utf-8",
           data: JSON.stringify(listProductId)
         }).done(function(response) {
            window.location.href = urlComparePage;
         }).fail(function(error) {
           console.error("error : " + error);
         });
});

window.addEventListener("load", (event) => {
    let dataList = JSON.parse(localStorage.getItem('listCompare')) || [];
    let listBtnCompare = $('.compare__item');
    for (const item of dataList) {
         listBtnCompare.each(function(index, element){
            if($(element).data('product-id') === item.id){
                $(element).addClass('active')
            }
         })
    }

    updateHtmlCompare(dataList);

    if ($('.stick-compare').is(':hidden') && dataList.length != 0) {
        $('.popup__compare').show();
    }
});