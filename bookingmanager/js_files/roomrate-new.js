$(function(){
	$('form.add-room-rate').submit(function(e){
		$('.start-date-error, .end-date-error').text('');
		if ($('#roomrate-add-startdate').val().trim() == "") {
			e.preventDefault();
			$('.start-date-error').text("Invalid start date!");
		}
		if ($('#roomrate-add-enddate').val().trim() == "") {
			e.preventDefault();
			$('.end-date-error').text("Invalid end date!");
		}
	});
});