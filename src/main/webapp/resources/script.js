document.addEventListener("DOMContentLoaded", function () {
    console.log("DOM fully loaded and parsed");

    (function () {
        var messageButtons = document.querySelectorAll(".message_btn");
        [].forEach.call(messageButtons, function(messageButton){
           messageButton.addEventListener("click", function (e) {
               e.preventDefault();
               e.stopImmediatePropagation();
               e.stopPropagation();

               var recepientId = this.getAttribute('data-recepient');

               document.open('/send-message/'+recepientId, 'wiadomosc', 'width=600, height=400');
           });
        });

    }());




});