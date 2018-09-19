$(() => {
    
    $('#submit').click(()=>{
        var requestBody = {
                "PaymentLinkName":$('#paymentLinkName').val()
        };
        $.ajax({
            url: "/rest/paymentDetails",
            type: "POST",
            data: requestBody,
            dataType: "json",
            timeout: 30000,
        });
    });

});
