window.onload = function() {
    initApp();
  };
function initApp() {
    document.getElementById('custom_login').addEventListener('click', toggleSignIn, false);
    (function ($) {
        "use strict";
    
    
        /*==================================================================
        [ Focus input ]*/
        $('.input100').each(function(){
            $(this).on('blur', function(){
                if($(this).val().trim() != "") {
                    $(this).addClass('has-val');
                }
                else {
                    $(this).removeClass('has-val');
                }
            })    
        })
      
      
        
        
        
    })(jQuery);
}
    
    
    function toggleSignIn() {
          
          var email = document.getElementById('useremail').value;
          var password = document.getElementById('userpass').value;
          if (email.length < 4) {
            alert('Please enter an email address.');
            return;
          }
          if (password.length < 4) {
            alert('Please enter a password.');
            return;
          }
        //   Sign in with email and pass.
        //   [START authwithemail]

            firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            
            // [START_EXCLUDE]
            // if (errorCode === 'auth/wrong-password') {
            //   alert('Wrong password.');
            // } else {
              alert(errorMessage);
            // }
            
            
            // [END_EXCLUDE]
          });        
        //   [END authwithemail]
        }
       
      

        