$(function(){

    /*var regEmail=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    $('#email').on('blur',function(){
        let email = $(this).val();
        if(regEmail.test(email)){
            $(this).removeClass('form-control is-invalid');
            $(this).addClass('form-control is-valid');
            $('#password-forgot-sumbit').removeClass('disabled');       
        }else{
            $(this).removeClass('form-control is-valid');
            $(this).addClass('form-control is-invalid');
            $('#password-forgot-sumbit').addClass('disabled');                        
        }
    });*/

    $('#password-forgot-sumbit').click(function(e){
        if($('#password-forgot-sumbit').hasClass('disabled')){
            e.preventDefault();
        }
    });

    $('#resend').click(function(){
        window.location.replace("http://localhost:8080/resend_email?email="+$('#emailAddressForResend').text());
    });
});
