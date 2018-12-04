<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="spring" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
        <style>
            body {
                height: 100vh;
                display: grid;
                place-content: center;
                background: linear-gradient(77deg, #1e5799 0%,#7db9e8 100%);
            }
            a:link, a:visited{
                color:red;
            }

        </style>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Open+Sans);
            .btn { display: inline-block; *display: inline; *zoom: 1; padding: 4px 10px 4px; margin-bottom: 0; font-size: 13px; line-height: 18px; color: #333333; text-align: center;text-shadow: 0 1px 1px rgba(255, 255, 255, 0.75); vertical-align: middle; background-color: #f5f5f5; background-image: -moz-linear-gradient(top, #ffffff, #e6e6e6); background-image: -ms-linear-gradient(top, #ffffff, #e6e6e6); background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#ffffff), to(#e6e6e6)); background-image: -webkit-linear-gradient(top, #ffffff, #e6e6e6); background-image: -o-linear-gradient(top, #ffffff, #e6e6e6); background-image: linear-gradient(top, #ffffff, #e6e6e6); background-repeat: repeat-x; filter: progid:dximagetransform.microsoft.gradient(startColorstr=#ffffff, endColorstr=#e6e6e6, GradientType=0); border-color: #e6e6e6 #e6e6e6 #e6e6e6; border-color: rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.1) rgba(0, 0, 0, 0.25); border: 1px solid #e6e6e6; -webkit-border-radius: 4px; -moz-border-radius: 4px; border-radius: 4px; -webkit-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05); -moz-box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05); box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05); cursor: pointer; *margin-left: .3em; }
            .btn:hover, .btn:active, .btn.active, .btn.disabled, .btn[disabled] { background-color: #e6e6e6; }
            .btn-large { padding: 9px 14px; font-size: 15px; line-height: normal; -webkit-border-radius: 5px; -moz-border-radius: 5px; border-radius: 5px; }
            .btn:hover { color: #333333; text-decoration: none; background-color: #e6e6e6; background-position: 0 -15px; -webkit-transition: background-position 0.1s linear; -moz-transition: background-position 0.1s linear; -ms-transition: background-position 0.1s linear; -o-transition: background-position 0.1s linear; transition: background-position 0.1s linear; }
            .btn-primary, .btn-primary:hover { text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.25); color: #ffffff; }
            .btn-primary.active { color: rgba(255, 255, 255, 0.75); }
            .btn-primary { background-color: #4a77d4; background-image: -moz-linear-gradient(top, #6eb6de, #4a77d4); background-image: -ms-linear-gradient(top, #6eb6de, #4a77d4); background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#6eb6de), to(#4a77d4)); background-image: -webkit-linear-gradient(top, #6eb6de, #4a77d4); background-image: -o-linear-gradient(top, #6eb6de, #4a77d4); background-image: linear-gradient(top, #6eb6de, #4a77d4); background-repeat: repeat-x; filter: progid:dximagetransform.microsoft.gradient(startColorstr=#6eb6de, endColorstr=#4a77d4, GradientType=0);  border: 1px solid #3762bc; text-shadow: 1px 1px 1px rgba(0,0,0,0.4); box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.5); }
            .btn-primary:hover, .btn-primary:active, .btn-primary.active, .btn-primary.disabled, .btn-primary[disabled] { filter: none; background-color: #4a77d4; }
            .btn-block { width: 100%; display:block; }

            * { -webkit-box-sizing:border-box; -moz-box-sizing:border-box; -ms-box-sizing:border-box; -o-box-sizing:border-box; box-sizing:border-box; }

            html { width: 100%; height:100%; overflow:hidden; }

            body { 
                width: 100%;
                height:100%;
                font-family: 'Open Sans', sans-serif;
                background: #092756;
                background: -moz-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%),-moz-linear-gradient(top,  rgba(57,173,219,.25) 0%, rgba(42,60,87,.4) 100%), -moz-linear-gradient(-45deg,  #670d10 0%, #092756 100%);
                background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -webkit-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -webkit-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background: -o-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -o-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -o-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background: -ms-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), -ms-linear-gradient(top,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), -ms-linear-gradient(-45deg,  #670d10 0%,#092756 100%);
                background: -webkit-radial-gradient(0% 100%, ellipse cover, rgba(104,128,138,.4) 10%,rgba(138,114,76,0) 40%), linear-gradient(to bottom,  rgba(57,173,219,.25) 0%,rgba(42,60,87,.4) 100%), linear-gradient(135deg,  #670d10 0%,#092756 100%);
                filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#3E1D6D', endColorstr='#092756',GradientType=1 );
            }
            .login { 
                position: absolute;
                top: 50%;
                left: 50%;
                margin: -150px 0 0 -150px;
                width:300px;
                height:300px;
            }
            .login h1 { color: #fff; text-shadow: 0 0 10px rgba(0,0,0,0.3); letter-spacing:1px; text-align:center; }

            .input1 { 
                width: 100%; 
                margin-bottom: 10px; 
                background: rgba(0,0,0,0.3);
                border: none;
                outline: none;
                padding: 10px;
                font-size: 13px;
                color: #fff;
                text-shadow: 1px 1px 1px rgba(0,0,0,0.3);
                border: 1px solid rgba(0,0,0,0.3);
                border-radius: 4px;
                box-shadow: inset 0 -5px 45px rgba(100,100,100,0.2), 0 1px 1px rgba(255,255,255,0.2);
                -webkit-transition: box-shadow .5s ease;
                -moz-transition: box-shadow .5s ease;
                -o-transition: box-shadow .5s ease;
                -ms-transition: box-shadow .5s ease;
                transition: box-shadow .5s ease;
            }
            input:focus { box-shadow: inset 0 -5px 45px rgba(100,100,100,0.4), 0 1px 1px rgba(255,255,255,0.2); }

        </style>


    </head>
    <body>

        <div class="login">
            <h1>Register</h1>
            <spring:form modelAttribute="user" action="adduser.htm" method="post">
                <spring:input path="username" id="usernamecheck" class="input1" type="text" placeholder="Username" required="required" ></spring:input>
                <spring:input path="firstname" class="input1" type="text" placeholder="First Name" required="required" ></spring:input>
                <spring:input path="lastname" class="input1" type="text" placeholder="Last Name" required="required" ></spring:input>
                <spring:input path="email" class="input1" type="email" placeholder="e-mail" required="required" ></spring:input>               
                <spring:input path="password" class="input1" type="password" placeholder="Password" required="required" id="first"></spring:input>
                    <input type="password" name="password1" class="input1" placeholder="Repeat Password" required="required" id="second"/>
                    <input type="checkbox" class="input1" id="checkbox1" value="check" style="width:4%;" required="required"/><span style="color:cyan;">Do you agree with the terms of use?</span>
                    <button id="button1" type="submit" disabled class="btn-block btn-large" >Register!</button>
                    <div id="passInfo"></div>
                    <div id="usermessage"></div>
            </spring:form>
        </div>

    <canvas></canvas>

    <script>
        $(document).ready(function () {
            $("#usernamecheck").keyup(function () {
                var text = $(this).val();
                $.ajax({
                    url: 'usernamecheck.htm?username=' + text,
                    contentType: 'application/json',
                    success: function (result) {
                        $("#usermessage").empty();
                        var jsonobj = $.parseJSON(result);
                        $.each(jsonobj, function (i, item) {
                            $p = $('<p>').text(item);
                        });
                        $("#usermessage").append($p);
                        if ($("#usermessage").text() === 'This username is available') {
                            $("#usermessage").css({'color': 'green'});
                        } else {
                            $("#usermessage").css({'color': 'red'});
                        }
                    }
                });
            });

        });
    </script>
    <script>
        $(document).ready(function () {

            $("#first,#second").keyup(function () {
                if ($("#first").val() === $("#second").val()) {
                    $("#passInfo").text("The password is the same!");
                    $("#passInfo").css({'color': 'green'});
                } else {
                    $("#passInfo").text("The password is not the same!");
                    $("#passInfo").css({'color': 'red'});
                }
            });
            $(document).on({
                click: function () {
                    if ($("#first").val() === $("#second").val() && $("#checkbox1:checked").val() !== undefined && $("#usermessage").text() === 'This username is available') {
                        $('#button1').prop('disabled', false);
                    } else {
                        $('#button1').prop('disabled', true);
                    }
                    if ($("#checkbox1:checked").val() !== undefined) {
                        $("#button1").addClass("btn btn-primary");
                    } else {
                        $("#button1").removeClass("btn btn-primary");
                    }
                },
                keyup: function () {
                    if ($("#first").val() === $("#second").val() && $("#checkbox1:checked").val() !== undefined && $("#usermessage").text() === 'This username is available') {
                        $('#button1').prop('disabled', false);
                    } else {
                        $('#button1').prop('disabled', true);
                    }
                }
            });
        });

    </script>
    <script type="text/javascript">

        var canvas = document.querySelector('canvas');
        var ctx = canvas.getContext('2d');
        var squids = new Array(32);
        var bubbles = new Array(54);
        var t = 0;
        //create squids
        for (var i = 0; i < squids.length; i++) {
            var s = 20, e = 160;
            squids[i] = {
                re: s + (Math.random() * e),
                g: s + (Math.random() * e),
                b: s + (Math.random() * e),
                x: Math.random() * innerWidth,
                y: Math.random() * innerHeight,
                vx: (0.5 - Math.random()) / 4,
                vy: 0.1 - Math.random(),
                r: 10 + (Math.random() * 40),
                a: []
            };
        }
        //create bubbles
        for (var i = 0; i < bubbles.length; i++) {
            bubbles[i] = {
                x: Math.random() * innerWidth,
                y: Math.random() * innerHeight,
                vx: 0.5 - Math.random(),
                vy: -0.2 - Math.random(),
                o: 0.05 + Math.random() * 0.1,
                r: 3 + Math.random() * 20
            };
        }
        var limit = function (d) {
            if (d.x < -d.r)
                d.x = innerWidth + d.r;
            if (d.x > innerWidth + d.r)
                d.x = -d.r;
            if (d.y < -d.r)
                d.y = innerHeight + d.r;
            if (d.y > innerHeight + d.r)
                d.y = -d.r;
        };
        var animate = function () {
            t++;
            canvas.width = innerWidth;
            canvas.height = innerHeight;
            bubbles.forEach(function (b) {
                b.x += b.vx;
                b.y += b.vy;
                limit(b);
                ctx.fillStyle = 'rgba(255,255,255,' + b.o + ')';
                ctx.beginPath();
                ctx.arc(b.x, b.y, b.r, Math.PI * 2, 0);
                ctx.fill();
            });
            squids.forEach(function (d) {
                var w = Math.sin((t + (d.r * 100)) / 10);
                d.x += d.vx * 4;
                d.y -= w + 1;
                d.y += d.vy;
                limit(d);
                var color1 = 'rgba(' + d.re + ',' + d.g + ',' + d.b + ',0.4)';
                var color2 = 'rgba(' + d.re + ',' + d.g + ',' + d.b + ',0.2)';
                ctx.fillStyle = color1;
                ctx.beginPath();
                ctx.arc(d.x, d.y, d.r, Math.PI + (-0.5 + d.vx) - (w / 4), (0.5 + d.vx) + (w / 4));
                ctx.fill();
                d.a.push({x: d.x, y: d.y - (d.r * 0.2)});
                if (d.a.length > d.r * 3)
                    d.a.splice(0, 1);
                d.a.forEach(function (p, i) {
                    ctx.fillStyle = color2;
                    ctx.fillRect(p.x, p.y, 2, 2);
                    if (i > d.a.length / 2) {
                        ctx.fillRect(p.x - (d.r / 4), p.y, 2, 2);
                        ctx.fillRect(p.x + (d.r / 4), p.y, 2, 2);
                    }
                    if (i > d.a.length / 3) {
                        ctx.fillRect(p.x + (d.r / 10), p.y - 10, 2, 2);
                        ctx.fillRect(p.x - (d.r / 10), p.y - 10, 2, 2);
                    }
                });
            });
            requestAnimationFrame(animate);
        };
        animate();
    </script>
</body>

</html>
