$(document).ready(function () {
	 $('input:checkbox').removeAttr('checked');
	 $('#items-table tr').click(function (event) {
		if (event.target.type !== 'checkbox') {
            $(':checkbox', this).trigger('click');
            
        }
	 });

	 $("input[type='checkbox']").change(function (e) {
    	if ($(this).is(":checked")) {
    		$(this).closest('tr').addClass("table-active");
        } else {
            $(this).closest('tr').removeClass("table-active");
        }
	 });
});
	     
function toggleItemStatus(status){
	if(validateCheckBoxes()){
	    $("#itemStatus").val(status);
	    $("#productForm").attr("action", "/toggleItemStatus");
		$("#productForm"). submit();
	}
}

function setItemRates(){
	if(validateCheckBoxes()){
		$("#productForm"). submit();
	}
}