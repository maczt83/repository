$(function(){
    var regName=/^([A-ZÁÉÍÓÖŐÜŰÚ]([a-záéíöüóőúű]+([- ])?|'|. ))+.?$/;

    var regEmail=/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    var regPhone=/^\d{8,13}$/;

    var title;
    var lastName;
    var firstName;
    var phoneNumber;
    var email;
    var taxNo;
    var vatID;
    var zip;
    var city;
    var country;
    var address1;
    var companyName;

    if ($('#guest-email').val()) able();
    
    $('#cityselect').hide();

    $('#guest-company-name').blur(function(){
        able();
        companyName = $(this).val();
        $(this).removeClass('is-invalid is-valid');
        if(!regName.test(companyName)){
                $(this).addClass('is-invalid');
                companyName = $(this).val();
        }else{
                $(this).addClass('is-valid');
                companyName = $(this).val();
                able();
        }
    })

    $('#guest-last-name').blur(function(){
        able();
        lastName = $(this).val();
        $(this).removeClass('is-invalid is-valid');
        if (!regName.test(lastName)) {
          $(this).addClass('is-invalid');
          lastName = $(this).val();
        } else {
          $(this).addClass('is-valid');
          lastName = $(this).val();
          able();
        }
    });

    $('#guest-first-name').blur(function(){
        able();
        firstName = $(this).val();
        $(this).removeClass('is-invalid is-valid');
        if (!regName.test(firstName)) {
          $(this).addClass('is-invalid');
          firstName = $(this).val();
        } else {
          $(this).addClass('is-valid');
          firstName = $(this).val();
          able();
        }
    });

    $('#guest-email').blur(function(){
            able();
        email = $(this).val();
        $(this).removeClass('is-invalid is-valid');
        if (!regEmail.test(email)) {
          $(this).addClass('is-invalid');
          email = $(this).val();
        } else {
          $(this).addClass('is-valid');
          email = $(this).val();
          able();
          $.ajax({
                url: "http://localhost:8080/guests/checkEmail",
                type: "get",
                data : { email : $(this).val() },
                success: function(response) {
                    if(response == false){
                        $('#guest-email').removeClass('is-invalid is-valid');
                        $('#guest-email').addClass('is-invalid');
                    }
                },
                error: function(xhr) {
                  //Do Something to handle error
                }
            });
        }

    });

    $('#guest-phonenumber').blur(function(){
            able();
        phoneNumber = $(this).val();
        $(this).removeClass('is-invalid is-valid');
        if (!regPhone.test(phoneNumber)) {
          $(this).addClass('is-invalid');
          phoneNumber = $(this).val();
        } else {
          $(this).addClass('is-valid');
          phoneNumber = $(this).val();
          able();
        }
    });

    $("#guest-country").blur((e) => {
        able();
        if ($(e.target).hasClass('is-invalid')) $(e.target).removeClass('is-invalid');
        if (!($(e.target).val())) $(e.target).addClass('is-invalid');
        if ($(e.target).val() !== "Hungary") {
            $('#guest-city').prop('readonly',false);
            if ($('#guest-city').hasClass('is-invalid')) $('#guest-city').removeClass('is-invalid')
        }
        able();
    });

    $("#guest-country").change((e) => {
        $("#guest-zip").val('')
        $("#guest-city").val('')
        if ($(e.target).val() !== "Hungary") {
            $('#guest-city').prop('readonly',false);
            if ($('#guest-city').hasClass('is-invalid')) $('#guest-city').removeClass('is-invalid')
        }
        able();
    });

    $("#guest-city").blur((e) => {
        able();
        if ($(e.target).hasClass('form-control is-invalid')) $(e.target).removeClass('is-invalid');
        if (!($(e.target).val())) $(e.target).addClass('is-invalid');
        able();
    });

    $("#guest-address").blur((e) => {
        able();
        if ($(e.target).hasClass('is-invalid')) $(e.target).removeClass('is-invalid');
        if (!($(e.target).val())) $(e.target).addClass('is-invalid');
        able();
    });

    function able() {
        if (!($('#guest-email').hasClass('is-invalid')
          || $('#guest-last-name').hasClass('is-invalid')
          || $('#guest-first-name').hasClass('is-invalid')
          || $('#guest-company-name').hasClass('is-invalid')
          || $('#guest-phonenumber').hasClass('is-invalid')
              || $('#guest-city').hasClass('is-invalid')
              || $('#guest-zip').hasClass('is-invalid')
              || $('#guest-country').hasClass('is-invalid')
              || $('#guest-address').hasClass('is-invalid'))){
          $('#submit').prop('disabled',false);;
        }else {
          $('#submit').prop('disabled',true);
        }
    };

    var options = {
        url: "http://localhost:8080/js/countries.json",
        getValue: "name",
        list: {
            match: {
                enabled: true
            },
            showAnimation: {
                    type: "fade",
                    time: 400,
                    callback: function() {}
            },
            hideAnimation: {
                    type: "slide",
                    time: 400,
                    callback: function() {}
            },
             onHideListEvent : function() {
		country = $("#guest-country").val();
		if(country === "Hungary"){
			$("#vat-div").hide();
			$("#tax-div").show();
                        $('#guest-city').prop('readonly',true);
		}else{
			$("#vat-div").show();
			$("#tax-div").hide();
		}
            }	
        }
    };

    $("#guest-country").easyAutocomplete(options);

    function getCityByZipCode(zipCode) {
      return iranyitoszamok.filter(
        function(iranyitoszamok) {
          return iranyitoszamok.zipCode == zipCode
        });
    };

    $("#guest-zip").blur((e) => {
        var regexp1 = /^\d{4,4}$/
        var regexp2 = /^H-\d{4,4}$/i;
        var zipCode = $(e.target).val().trim();
        if(regexp2.test(zipCode)) {
             zipCode= $(e.target).val().trim().slice(-4);
             console.log(zipCode);
        }
        able()
        if ($(e.target).hasClass('is-invalid')) $(e.target).removeClass('is-invalid')
        if ($(e.target).hasClass('is-invalid')) $(e.target).removeClass('is-invalid')
        able()
        if ($("#guest-country").val() === "Hungary") {
            if ($('#guest-city').hasClass('is-invalid')) $('#guest-city').removeClass('is-invalid')
            if (regexp1.test(zipCode) || regexp2.test(zipCode)) {
                var found = getCityByZipCode(zipCode)
                if (found.length == 1) {
                    $('#cityinput').show();
                    $('#cityselect').hide();
                    $('#guest-city').prop('readonly',false)
                    $("#guest-city").val(found[0].cityName)
                    $("#guest-city").prop('readonly',true)
                } else {
                    $('#cityinput').hide();
                    $('#cityselect').show();
                    $('#cityselectopt').children().remove();
                    for (let cityObj of found) { 
                        $('#cityselectopt').append('<option value="'+cityObj.cityName+'">'+cityObj.cityName+'</option>')
                    } 
                }
            } else {
                $('#cityinput').show();
                $('#cityselect').hide();
                $("#guest-city").val('')
                $(e.target).addClass('is-invalid')
            }
        }
    });

    $("#guest-zip").change((e) => {
        $(e.target).trigger("blur")
    });

     if($('#guest-country').val() != null && $('#guest-country').val() == 'Hungary'){
     	$("#vat-div").hide();
		$("#tax-div").show();
     }else{
     	$("#vat-div").show();
		$("#tax-div").hide();
     }

     if($('#guest-country').val() == ""){
     	$("#vat-div").hide();
		$("#tax-div").hide();
     }
     $('#guest-company-name').hide();
     $('#guest-company-name-div').hide();
     $('#guest-title').change(function(){
     	var title=$(this).val();
     	if(title === "COMPANY"){
     		$('#guest-first-name').hide();
     		$('#guest-last-name').hide();
     		$('#guest-first-name-div').hide();
     		$('#guest-last-name-div').hide();
     		$('#guest-company-name').show();
     		$('#guest-company-name-div').show();
     	}else{
     		$('#guest-first-name').show();
     		$('#guest-last-name').show();
     		$('#guest-first-name-div').show();
     		$('#guest-last-name-div').show();
     		$('#guest-company-name').hide();
     		$('#guest-company-name-div').hide();
     	}
     });
     if($('#guest-title').val() === "COMPANY"){
     		$('#guest-first-name').hide();
     		$('#guest-last-name').hide();
     		$('#guest-first-name-div').hide();
     		$('#guest-last-name-div').hide();
     		$('#guest-company-name').show();
     		$('#guest-company-name-div').show();
     }

});