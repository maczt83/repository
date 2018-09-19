$(function (){
	$('#password-alert').hide();
	$('.to-check').keyup( function(){
		if(($('#first-new-password').val() != $('#second-new-password').val())){
			$("#first-new-password").addClass('is-invalid');
			$("#second-new-password").addClass('is-invalid');
			$('#password-alert').show();
			$('#submit-btn').prop('disabled',true);
		}else{
			$("#first-new-password").removeClass('is-invalid').addClass('is-valid');
			$("#second-new-password").removeClass('is-invalid').addClass('is-valid');
			$('#password-alert').hide();
			$('#submit-btn').prop('disabled',false);
		}
	});

        $('#submit-btn').click(function(e){
            if ($('#first-new-password').val() === "") e.preventDefault();
        });


});