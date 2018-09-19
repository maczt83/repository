$(function(){

    var intervalID;
    if($('#countback').length){
        var countback = 5;
        $('#countback').html("Congratulations! You have succesfully changed your password! Your browser will automatically redirect you to the index page after "+countback+" seconds.");
        clearInterval(intervalID);
        intervalID = setInterval(function(){
          if( countback == 0){
            clearInterval(intervalID);
            window.location.replace("http://localhost:8080/index");
          }else{
            $('#countback').html("Congratulations! You have succesfully changed your password! Your browser will automatically redirect you to the index page after " + --countback + " seconds.");
          }
        },1000);

    };


});
