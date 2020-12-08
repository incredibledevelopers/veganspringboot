$(document).ready(function () {
		$('#ordersTable tr').click(function (event) {
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
	    
	    $('#cancellationReason').dialog({
	        autoOpen: false,
	        width: 300,
	        modal : true,
	        open: function(event, ui) {
	        	  $('.ui-widget-overlay').css({ opacity: '.5' });
	        },
	        buttons: {
	            "Ok": function() {
	            	if(isBlank('#cancellationReasonText')){
	            		$('#cancellationReasonText').focus();
	            	}else{
	            		$('#cancellationReasonVal').val($('#cancellationReasonText').val());
	            		$("#ordersForm").attr("action", "/markCancelled");
	           		 	$("#ordersForm"). submit();
	            	}
	            },
	            "Cancel": function() {
	                $(this).dialog("close");
	            }
	        }
	    });
	    
	    $('#deliveryCharges').dialog({
	        autoOpen: false,
	        width: 300,
	        modal : true,
	        open: function(event, ui) {
	        	  $('.ui-widget-overlay').css({ opacity: '.5' });
	        },
	        buttons: {
	            "Ok": function() {
	            	if(isBlank('#deliveryChargesText')){
	            		$('#deliveryChargesText').focus();
	            	}else{
	            		$('#deliveryChargesVal').val($('#deliveryChargesText').val());
	            		$("#ordersForm").attr("action", "/markDeliveredWithCharges");
	           		 	$("#ordersForm"). submit();
	            	}
	            },
	            "Cancel": function() {
	                $(this).dialog("close");
	            }
	        }
	    });
	    //$('#myModal').modal();
		
});

function markDelivered(){
	if(validateCheckBoxes()){
	    $("#ordersForm"). submit();
	}
}

function showMarkDelWithChargesModal(){
	if(validateCheckBoxes()){
		$('#deliveryChargesText').val('');
		$('#deliveryCharges').dialog('open');
		return false;
	}
}

function showCancelOrderModal(){
	if(validateCheckBoxes()){
		$('#cancellationReasonText').val('');
	    //$('#cancellationReason').modal();
	    $('#cancellationReason').dialog('open');
	    $('#cancellationReasonText').focus();
	    return false;
	}
}
