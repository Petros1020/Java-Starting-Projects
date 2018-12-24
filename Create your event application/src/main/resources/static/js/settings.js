(function () {
    function floatLabel(inputType) {
        $(inputType).each(function () {
            var input = $(this).find("select, textarea,input");
            var label = $(this).find("label");
            // on focus add cladd active to label
            input.focus(function () {
                input.next().addClass("active");
//        console.log("focus");

            });
            //checks if there are inputs from reloading
            if (!(input.val() === '' || input.val() === 'blank')) {
                input.next().addClass("active");
            }
            //on blur check field and remove class if needed
            input.blur(function () {
                if (input.val() === '' || input.val() === 'blank') {
                    label.removeClass();
                }
            });
        });
    }
    // just add a class of "floatLabel to any group you want to have the float label interactivity"
    floatLabel(".float-label");


//////  Just a bunch of fluff for other interactions  ////////////////////////////////////////////////////////  

    //for the pw field - toggle visibility
    $(".eye").on("click", function () {
        var $this = $(this);
        if (!$this.is(".show")) {
            $this.addClass("show")
                    .removeClass("fa-eye-slash")
                    .addClass("fa-eye").next()
                    .attr("type", "text");
        } else {
            $this.removeClass("show")
                    .addClass("fa-eye-slash")
                    .removeClass("fa-eye")
                    .next().attr("type", "password");
        }
    });

    //modal close
    $(".close").on("click", function () {
        $(this).parent().removeClass("show");
        $("#clear").click();
    })

    //submit button dirty validation ^-^
//    $("button[type='submit']").on("click", function () {
//        if (!$("input, select, textarea").val()) {
//            $(this).text("Please enter all Fields");
//        } else {
//            $(".confirm").addClass("show");
//        }
//        return false;
//    })
    //just for reset button
    $("#clear").on("click", function () {
        $(".active").removeClass("active");
        if (!(input.val() === '' || input.val() === 'blank')) {
            input.next().addClass("active");
        }
    });

})(jQuery);


function readURL(input) {
    console.log("ok");
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#profileimage')
                    .attr('src', e.target.result)
                    .width(500);
        };
        $("#fileuploadlabel").toggle();
        $("#fileuploadlabeltext").toggle();
        reader.readAsDataURL(input.files[0]);
    }
}