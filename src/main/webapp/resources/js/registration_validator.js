var emailRegex = new RegExp($("#inputEmail4").attr("pattern"))
var passwordRegex = new RegExp($("#inputPassword4").attr("pattern"))
var firstNameRegex = new RegExp($("#inputFirstName").attr("pattern"))
var lastNameRegex = new RegExp($("#inputLastName").attr("pattern"))
var regexMap = {
    'email': emailRegex,
    'password': passwordRegex,
    'firstName': firstNameRegex,
    'lastName': lastNameRegex,
}
jQuery(function () {
    $(".form-control").on("input", function () {
        if ($(this).attr("type") in regexMap && !regexMap[$(this).attr("type")].test($(this).val())) {
            $(this).attr("style", "border: 2px solid red")
            $(this).attr("not-validated")
            $("#error-"+$(this).attr("id")).removeAttr("hidden")
        }
        else {
            $(this).removeAttr("style")
            $(this).removeAttr("not-validated")
            $("#error-"+$(this).attr("id")).attr("hidden", "")
            readyForSubmit: {
                for (let input of $("input.form-control").get()){

                    if (input.hasAttribute("not-validated")) {
                        break readyForSubmit
                    }
                }
                $("#submit-button").removeAttr("disabled")
            }


        }

    })
})
