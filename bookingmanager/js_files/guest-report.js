$(function(){

	$("#input-date").change(function(){
		var date = $(this).val();
		$.ajax({
			  url: "http://localhost:8080/report/date",
			  type: "get",
			  data : { date : $(this).val() },
			  success: function(response) {
			  		$('#leaving-guests tbody').children('tr').remove();
			  		$('#arriving-guests tbody').children('tr').remove();
			  		response.arrivingGuests.forEach(function(item, index){
					    var $tr = $('<tr></tr>');
					    var $nametd = $('<td></td>').text(response.arrivingGuests[index].name);
					    var $roomtd = $('<td></td>').text(response.arrivingGuests[index].roomNumber);
					    var $checkIntd = $('<td></td>').text(response.arrivingGuests[index].checkInTime);
					    $tr.append($nametd).append($roomtd).append($checkIntd);
					    $('#arriving-guests tbody').append($tr);
				    })
				    response.leavingGuests.forEach(function(item, index){
				    	
					    var $tr = $('<tr></tr>');
					    var $nametd = $('<td></td>').text(response.leavingGuests[index].name);
					    var $roomtd = $('<td></td>').text(response.leavingGuests[index].roomNumber);
					    if(response.leavingGuests[index].paid == 0){
					    	var $paidtd = $('<td></td>').text("Unpaid");
					    }else{
					    	var $paidtd = $('<td></td>').text("Paid");
					    }
					    
					    $tr.append($nametd).append($roomtd).append($paidtd);
					    $('#leaving-guests tbody').append($tr);
				    })
			  },
			  error: function(xhr) {
			    //Do Something to handle error
			  }
		});
		
	})

})