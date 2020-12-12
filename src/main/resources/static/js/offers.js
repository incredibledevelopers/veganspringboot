function toggleValidity(status){
	if(validateCheckBoxes()){
		$("#offerStatus").val(status);
	    $("#offersForm"). submit();
	}
}

function addOffer(){
	if ($.trim($('#addOfferText').val()) == ''){
		alert('Offer text is required.');
		$('#addOfferText').focus();
		return false;
	}else{
		$('#offersForm').attr('action', 'addOffer');
		$("#offersForm"). submit();
	} 
}
