$('#name_gr_category').on('change',async function(){
       let url = '/yasuki/admin/manager-product/change-group-category';
       let data = { id : $('#name_gr_category').val() }
       let listCateGroupItems = await callAjaxPromise(url, 'GET', data) || [];
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