$(function(){
	$('input[type="checkbox"]').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass   : 'iradio_square-green',
        increaseArea : '20%'
    });

	$('input[type="radio"]').iCheck({
        radioClass   : 'iradio_square-green',
        increaseArea : '20%'
    });
	
	
	/*
    $('form.add-room').submit(function(e){
		var capacity    = $('#room-add-capacity').val();
		var roomtype    = $('#room-add-roomtype').val();
		var building    = $('#room-add-building').val();
		var floor       = $('#room-add-floor').val();
		var roomnumber  = $('#room-add-roomnumber').val();
		var extrabed    = $('#room-add-extrabed').val();

        if (validation(capacity, roomtype, building, floor, roomnumber, extrabed)) {
            e.preventDefault();
			// ??
        }
    });
	
	
	function validation(capacity, roomtype, building, floor, roomnumber, extrabed) {
		$('.is-invalid').removeClass('is-invalid');
		var isFormValid = true;
		if (capacity <= 0) {
			$('#room-add-capacity').addClass('is-invalid');
			isFormValid = false;
		}
		if (roomtype === 0) {
			$('#room-add-roomtype').addClass('is-invalid');
			isFormValid = false;
		}
		if (building <= 0) {
			$('#room-add-building').addClass('is-invalid');
			isFormValid = false;
		}
		if (roomnumber <= 0) {
			$('#room-add-roomnumber').addClass('is-invalid');
			isFormValid = false;
		}
		if (extrabed < 0) {
			$('#room-add-extrabed').addClass('is-invalid');
			isFormValid = false;
		}
	};*/
});
