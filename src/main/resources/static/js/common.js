function validateCheckBoxes(){
	if($('input[type=checkbox]:checked').length == 0){
	    alert ( "Please select at least one item." );
	    return false;
	}
	return true;
}

function isBlank(fieldId){
	return $(fieldId).val().trim().length == 0;
}

$(document).ready(function () {
	$('#filter').keyup(function () {

        var rex = new RegExp($(this).val(), 'i');
        $('.searchable tr').hide();
        $('.searchable tr').filter(function () {
            return rex.test($(this).text());
        }).show();

    })
});