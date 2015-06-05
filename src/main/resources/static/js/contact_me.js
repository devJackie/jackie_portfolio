/**
 * Created by devjackie83@gmail.com on 2015-06-05.
 */
var console = window.console || {log:function() {}};

var UTIL_LOADINGBAR = {
    show : function() {
        var height = "";


        if(!!window.innerHeight) {
            height = window.innerHeight;
        } else {
            height = document.documentElement.clientHeight;
        }
        $("#loadingbar .loading").css("top", $(window).scrollTop() + (height / 2));
        $("#loadingbar").show();
        $(window).on("scroll", function(){
            $("#loadingbar .loading").css("top", $(window).scrollTop() + (height / 2));
        });
    },
    hide : function() {
        setTimeout(function(){
            $("#loadingbar").hide();
        }, 500);
        $(window).off("scroll");
    }
};

var AJAX = {
    _loading_bar_flag : "Y",
    _timeOut : 20000,//20 seconds
    _successAct : "",
    _failAct : "",
    _url : "",
    _data : "",
    _ajax : function(fn){
        $.ajax({
            type : "POST",
            url : this._url,
            dataType : "json",
            data : this._data,
            timeout : this._timeOut,
            beforeSend : function(xhr) {
                if(AJAX._loading_bar_flag == 'Y')	UTIL_LOADINGBAR.show();
            },
            success : fn,
            complete : function(xhr, status) {
                if(AJAX._loading_bar_flag == 'Y')	UTIL_LOADINGBAR.hide();

                if(xhr.status == "403"){
//                        location.href = "/main/nigol.home.do";
                }
            },
            error : function(xhr, status, error) {
                eval(AJAX._failAct);

                if(xhr.status == "403"){
//                        location.href = "/main/nigol.home.do";
                }
            }
        });
    },

    _ajaxCall : function(paramObj, successAct, failAct, ajaxName, loading_bar, time_out, sync){
        try{
            this._loading_bar_flag = loading_bar;
            this._timeOut = time_out;
            this._data = paramObj;
            this._url = ajaxName+ "?time=" + new Date().getTime();
            this._successAct = successAct;
            this._failAct = failAct;
            this._ajax(this._ajaxCallSuccess);
        }catch(e){
            console.log("_ajaxCall Exception");
        }
    },
    _ajaxCallSuccess : function(json){
        try{
            if(json != null && json != undefined && json != ""){
                if(AJAX._successAct != null && AJAX._successAct != undefined && AJAX._successAct != ""){
                    eval(AJAX._successAct)(json);
                }
            }else{
                if(AJAX._failAct != null && AJAX._failAct != undefined && AJAX._failAct != ""){
                    eval(AJAX._failAct)();
                }
            }
        }catch(e){
            console.log("_ajaxCallSuccess Exception");
        }
    }

};

$(function() {
    $("input,textarea").jqBootstrapValidation({
        SEND_MAIL_URL : "/contact/send-mail",

        ajaxFailed : function (XMLHttpRequest, textStatus, errorThrown) {
            alert("네트워크가 원할하지 않습니다. 잠시 후 다시 시도해주세요.");
            console&&console.log(XMLHttpRequest, textStatus, errorThrown);
        },

        preventSubmit: true,
        submitError: function($form, event, errors) {
            // additional error messages or events
            console.log("submitError");
        },
        submitSuccess: function($form, event) {
            // Prevent spam click and default submit behaviour
            $("#btnSubmit").attr("disabled", true);
            event.preventDefault();
            
            // get values from FORM
            var name = $("input#name").val();
            var email = $("input#email").val();
            var phone = $("input#phone").val();
            var message = $("textarea#message").val();
            var firstName = name; // For Success/Failure Message
            // Check for white space in name for Success/Fail message
            if (firstName.indexOf(' ') >= 0) {
                firstName = name.split(' ').slice(0, -1).join(' ');
            }

            AJAX._ajaxCall(
                {name:name, phone:phone, email: email, message: message},
                function(json) {
                    try {
                        if (json.result && json.result != null && json.result != undefined) {
                            if (json.result.indexOf('success') >= 0) {
                                // Enable button & show success message
                                $("#btnSubmit").attr("disabled", false);
                                $('#success').html("<div class='alert alert-success'>");
                                $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                                    .append("</button>");
                                $('#success > .alert-success')
                                    .append("<strong>Your message has been sent. </strong>");
                                $('#success > .alert-success')
                                    .append('</div>');

                                //clear all fields
                                $('#contactForm').trigger("reset");
                            } else {
                                // Fail message
                                $('#success').html("<div class='alert alert-danger'>");
                                $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
                                    .append("</button>");
                                $('#success > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!");
                                $('#success > .alert-danger').append('</div>');
                                //clear all fields
                                $('#contactForm').trigger("reset");
                            }
                        }
                    } catch (e) {
                        console.log(e);
                    }
                },
                $.ajaxFailed,
                "/contact/send-mail",
                "Y"
            );





            //$.ajax({
            //    url: "/contact/send-email",
            //    type: "GET",
            //    data: {
            //        name: name,
            //        phone: phone,
            //        email: email,
            //        message: message
            //    },
            //    dataType: "json",
            //    cache: false,
            //    success: function(json) {
            //        try {
            //            if (json.result && json.result != null && json.result != undefined) {
            //                console.log(">> in");
            //                if (json.result.indexOf('success') >= 0) {
            //                    // Enable button & show success message
            //                    $("#btnSubmit").attr("disabled", false);
            //                    $('#success').html("<div class='alert alert-success'>");
            //                    $('#success > .alert-success').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            //                        .append("</button>");
            //                    $('#success > .alert-success')
            //                        .append("<strong>Your message has been sent. </strong>");
            //                    $('#success > .alert-success')
            //                        .append('</div>');
            //
            //                    //clear all fields
            //                    $('#contactForm').trigger("reset");
            //                } else {
            //                    // Fail message
            //                    $('#success').html("<div class='alert alert-danger'>");
            //                    $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            //                        .append("</button>");
            //                    $('#success > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!");
            //                    $('#success > .alert-danger').append('</div>');
            //                    //clear all fields
            //                    $('#contactForm').trigger("reset");
            //                }
            //            }
            //        } catch (e){
            //            console.log(e);
            //        }
            //    },
            //    error: function(e) {
            //        console.log(">>>>>>>>>>>>>> error : "+e);
            //        // Fail message
            //        $('#success').html("<div class='alert alert-danger'>");
            //        $('#success > .alert-danger').html("<button type='button' class='close' data-dismiss='alert' aria-hidden='true'>&times;")
            //            .append("</button>");
            //        $('#success > .alert-danger').append("<strong>Sorry " + firstName + ", it seems that my mail server is not responding. Please try again later!");
            //        $('#success > .alert-danger').append('</div>');
            //        //clear all fields
            //        $('#contactForm').trigger("reset");
            //    }
            //})
        },
        filter: function() {
            return $(this).is(":visible");
        }
    });

    $("a[data-toggle=\"tab\"]").click(function(e) {
        e.preventDefault();
        $(this).tab("show");
    });
});

// When clicking on Full hide fail/success boxes
$('#name').focus(function() {
    $('#success').html('');
});
