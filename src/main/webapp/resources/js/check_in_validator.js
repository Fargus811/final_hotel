$("input.form-control[type='date']").on("change", function () {
    if ($("input.form-control[name='"+ ($(this).attr('name') == 'startDate'?'end':'start') + "Date']").val() != "") {
        var startDate = new Date(Date.parse($("input.form-control[name='startDate']").val()))
        var endDate = new Date(Date.parse($("input.form-control[name='endDate']").val()))
        var today = new Date(Date.now()).setHours(0,0,0,0)
        if (startDate.getTime() >= today) {
            if (endDate.getTime() > today) {
                if (startDate.getTime() < endDate.getTime()) {
                    $("input.form-control[type='date']").each(function () {
                        $(this).removeAttr("style")
                        $(this).removeAttr('not-validated')
                        $('.error-message[for="' + $(this).attr('name') + '"]').attr('hidden', '')
                        $("button#create-booking").removeAttr('disabled')
                    })
                } else {
                    // start date is after end date
                    $(this).attr("style", "border: 2px solid red")
                    $('.error-message[for="' + $(this).attr('name') + '"]').removeAttr('hidden')
                    $("button#create-booking").attr('disabled', '')
                }
            } else {
                // end date is before today
                $("input.form-control[name='endDate']").attr("style", "border: 2px solid red")
                $('.error-message[for="endDate"]').removeAttr('hidden')
                $("button#create-booking").attr('disabled', '')
            }
        } else {
            // start date is before today
            $("input.form-control[name='startDate']").attr("style", "border: 2px solid red")
            $('.error-message[for="startDate"]').removeAttr('hidden')
            $("button#create-booking").attr('disabled', '')
        }
    }
})
