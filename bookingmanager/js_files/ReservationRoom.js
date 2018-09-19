$(function(){

	$("#input-to-date, #input-from-date").change(function(){
		$('.is-invalid').removeClass('is-invalid');
		var fromDate = $("#input-from-date").val();
		var toDate = $("#input-to-date").val();
		var isValid = true;
		if(fromDate == ""){
			$("#input-from-date").addClass('is-invalid');
			isValid = false;
		}
		if(toDate == ""){
			$("#input-to-date").addClass('is-invalid');
			isValid = false;
		}
		if(isValid){
                    $.ajax({
                            url: "http://localhost:8080/reservation/room",
                            type: "get",
				  data : { fromDate : $('#input-from-date').val(),
                                            toDate : $('#input-to-date').val()},
                            success: function(response){
                                    $('#select-room').children('.default').siblings().remove();
                                    $.each(response, function(index, value){
                                            var $option = $('<option></option>')
                                                        .attr('value', value.id)
                                                        .text(value.roomNumber);
                                            $('#select-room').append($option);
                                    });
                            }                
                });
                }
});
});