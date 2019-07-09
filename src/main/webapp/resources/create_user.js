document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM fully loaded and parsed");

    (function () {

        var password = document.querySelector("#password");
        var repeatPassword = document.querySelector("#repeat_password");
        var passwordValue;
        var repeatPasswordValue;
        password.addEventListener("input", function (e) {
            passwordValue=this.value;
            console.log(passwordValue);
        })

        repeatPassword.addEventListener("input", function (e) {
            repeatPasswordValue=this.value;
            console.log(repeatPasswordValue);
        })

        document.querySelector("#submit_btn").addEventListener("click", function (evt) {
            console.log(repeatPasswordValue);
            if(repeatPasswordValue===undefined || passwordValue!==repeatPasswordValue){
                evt.preventDefault();
                alert('Passwords dont match');
            }

        })

    }());




});