
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
				  url: "http://localhost:8080/report/room",
				  type: "get",
				  data : { fromDate : $('#input-from-date').val(),
				  			toDate : $('#input-to-date').val() },
				  success: function(response) {
				  	$('#input-from-date').css('border', '0px solid black');
				  	$('#input-to-date').css('border', '0px solid black');
				  	$('#from-date-error').addClass('invisible').text("");
				  	$('#to-date-error').addClass('invisible').text("");
				  	if(response.status == "SUCCESS"){
				  		$('#available-room-table tbody').children('tr').remove();
				  		response.availableRooms.forEach(function(item,index){
				  			var $tr = $('<tr></tr>');
				  			var $roomNumbertd = $('<td></td>').text(response.availableRooms[index].roomNumber);
				  			var $roomTypetd = $('<td></td>').text(response.availableRooms[index].roomType);
				  			$tr.append($roomNumbertd).append($roomTypetd);
				  			$('#available-room-table tbody').append($tr);
				  		})
				  		$('#reserved-room-table tbody').children('tr').remove();
				  		response.reveservedRooms.forEach(function(item,index){
				  			var $tr = $('<tr></tr>');
				  			var $roomNumbertd = $('<td></td>').text(response.reveservedRooms[index].roomNumber);
				  			var $roomTypetd = $('<td></td>').text(response.reveservedRooms[index].roomType);
				  			var $roomStartDate = $('<td></td>').text(response.reveservedRooms[index].startDate);
				  			var $roomEndDate = $('<td></td>').text(response.reveservedRooms[index].endDate);
				  			$tr.append($roomNumbertd).append($roomTypetd).append($roomStartDate).append($roomEndDate);
				  			$('#reserved-room-table tbody').append($tr);
				  		})
				  	}else{
				  		response.errors.forEach(function(item, index){
				  			if(response.errors[index] == "Please enter a start date"){
				  				$('#input-from-date').css('border', '1px solid red');
				  				$('#from-date-error').removeClass('invisible').text(response.errors[index]);
				  			}else if(response.errors[index] == "Please enter an end date"){
				  				$('#input-to-date').css('border', '1px solid red');
				  				$('#to-date-error').removeClass('invisible').text(response.errors[index]);
				  			}else if(response.errors[index] == "Please provide a valid date."){
				  				$('#input-to-date').css('border', '1px solid red');
				  				$('#to-date-error').removeClass('invisible').text(response.errors[index]);
				  			}
				  		});
				  	}
				  },
				  error: function(xhr) {
				    //Do Something to handle error
				  }
			});
		}
	})

})