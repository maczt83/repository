$(() => {
 
  
    $('.szepkartyapocket').hide();
    
    $('#provider').change((e) => {
        if ($(e.target).children(':selected').hasClass('szepkartya')) {  
            $('.szepkartyapocket').show();
            if (!$('#pocket').val()) $('#pocket').addClass('is-invalid');
        } else {
            $('.szepkartyapocket').hide();
            $('#pocket').text('');
            if (!$('#pocket').hasClass('is-invalid')) $('#pocket').removeClass('is-invalid');
        }
        if ($(e.target).val()==='OTPSimple') $('#currency').prop('disabled',true);
        else {
            if ($('#currency').prop('disabled')) $('#currency').prop('disabled', false);
        }
    });

    $('#amount').keyup((e) => {
        var regexp=/^\d*$/;
        if(!(regexp.test($(e.target).val()))) $(e.target).addClass('is-invalid');
        else if ($(e.target).hasClass('is-invalid')) $(e.target).removeClass('is-invalid');
    });


});
