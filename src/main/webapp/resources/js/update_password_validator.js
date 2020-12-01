var passwordRegex = new RegExp($("#newPassword").attr("pattern"))

function validate() {
    $('#newPassword').removeAttr("style")
    $('#newPassword').removeAttr("not-validated")
    $('#confirmPassword').removeAttr("style")
    $('#confirmPassword').removeAttr("not-validated")
    $("#updatePasswordButton").removeAttr("disabled")
}

jQuery(function () {
    $(".form-control#newPassword, .form-control#confirmPassword").on("input", function () {
        if ($("#confirmPassword").val() != "") {
            if ($("#newPassword").val() == $("#confirmPassword").val()) {
                validate()
                $("#error-" + $(this).attr("id")).attr("hidden", "")
            } else {
                $(this).attr("style", "border: 2px solid red")
                $(this).attr("not-validated")
                $("#error-" + $(this).attr("id")).removeAttr("hidden")
                $("#updatePasswordButton").attr("disabled", "")
            }
        }
        if (passwordRegex.test($(this).val())) {
            validate()
        }
        else {
            $(this).attr("style", "border: 2px solid red")
            $(this).attr("not-validated")
            $("#error-"+$(this).attr("id")).removeAttr("hidden")
            $("#updatePasswordButton").attr("disabled", "")
            }
    })
})
