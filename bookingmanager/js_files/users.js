$(function(){
    $('table').on('click', '.delete-user', function(){
            var id = $(this).parent().siblings('#userid').text();
            if(confirm('Are you sure you want to activate/deactivate this user?')){
                    $.ajax({
                            url : '/users/'+id,
                            type : 'POST',
                            success : (response) =>{                                
                                location.reload();
                        }
                    });

            }
    });
    
});
