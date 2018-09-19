$(() => {

    $("#search-box").val('');
    showDB();
    
    function showDB (){
        $.ajax({
            url: '/guests/showAll',
            method: "GET",
            success: inCaseSuccess
        });
    }

    $('#show_btn').click(()=>{
        $("#search-box").val('');
        showDB();
    });

    $('#search-box').blur((e)=>{
        if (!$(e.target).val()) showDB();
    });

    var globalTimeout = null;
    var previousSearchString = '';
    $("#search-box").keyup((e)=>{
        let searchString = $(e.target).val();
        if (searchString != '') { 
            previousSearchString = searchString;
            if (globalTimeout != null) {
                clearTimeout(globalTimeout);
            }
            globalTimeout = setTimeout(function() {
                globalTimeout = null;
                $.ajax({
                    url: "/guests/search",
                    method: "GET",
                    data: { name : searchString },
                    success: (response) => {
                        inCaseSuccess (response)
                    }
                });
            }, 300);
        } else {
            showDB();
        }
    });

    function inCaseSuccess (response) {
        $('.results-table').not('.prototype').remove();
            if (response.length !== 0) {
                $('.table').removeClass('d-none');
                response.forEach (element => {
                    var $clone = $('.prototype').clone();
                    $clone.removeClass('d-none prototype');
                    var $collapseInitiatorChildren = $clone.find('.collapse-initiator').children();
                    if (element.companyName) {
                        $collapseInitiatorChildren.filter('.guestName').text(element.companyName);
                    } else {
                        $collapseInitiatorChildren.filter('.guestName').text(element.lastName+" "+element.firstName);
                    }
                    
                    $collapseInitiatorChildren.filter('.email').text(element.emailAddress);
                    $collapseInitiatorChildren.filter('.phone').text(element.phoneNumber);
                    var $collapseTargetChildren = $clone.find('.collapse-target table tr').children();
                    $collapseTargetChildren.filter('.country').find('span').text(element.country);
                    $collapseTargetChildren.filter('.zip').find('span').text(element.zip);
                    $collapseTargetChildren.filter('.city').find('span').text(element.city);
                    $collapseTargetChildren.filter('.address').find('span').text(element.address1);
                    var ifHungary = 'Tax nr.: <span style="font-weight: normal">'+element.taxNo+'</span>';
                    var notHungary = 'VAT ID.: <span style="font-weight: normal">'+element.vatID+'</span>';
                    $collapseTargetChildren.filter('.taxvat').html((element.country==='Hungary')? ifHungary:notHungary);

                    var $bookGuestBtn = $clone.find('.book-guest-btn');
                    $bookGuestBtn.attr('href', $bookGuestBtn.attr('href') + element.id);
					
					var $editGuestBtn = $clone.find('.edit-guest-btn');
                    $editGuestBtn.attr('href', $editGuestBtn.attr('href') + element.id);

                    $('.results-table.prototype').before( $clone );
                })
                $('.results-table').on('click', '.row-btn', (e) => {
                    e.stopPropagation();
                });
                $('.results-table').on('mouseenter', '.row-btn', (e) => {
                    $(e.target).css("opacity","1");
                });
                $('.results-table').on('mouseleave', '.row-btn', (e) => {
                    $(e.target).css("opacity","0.2");
                });
                $('.results-table').on('click', '.collapse-initiator', (e) => {
                    $(e.target).closest('.collapse-initiator').next().collapse('toggle');
                });
            } 
    }
    
    

})
