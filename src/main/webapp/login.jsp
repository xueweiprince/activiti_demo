<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="login-bg">
<head>
	<title>登录</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />
    
        <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/signin.css" type="text/css" media="screen" />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <div class="row-fluid login-wrapper">
        <a href="index.html">
            <img class="logo" src="img/logo4.png" />
        </a>

        <div class="span4 box">
            <div class="content-wrap">
                <h6>登 录</h6>
                <form action="login.do" method="post">
                <input class="span12" name="username" type="text" placeholder="Username" />
                <input class="span12" name="password" type="password" placeholder="Your password" />
                <label class="label label-warning hide">username or password invalidate!</label>
                <a href="#" class="forgot">Forgot password?</a>
                <div class="remember">
                    <input id="remember-me" type="checkbox" />
                    <label for="remember-me">Remember me</label>
                </div>
                <a class="btn-glow primary login" href="javascript:$('form').submit()">Log in</a>
                </form>
            </div>
        </div>

        <div class="span4 no-account">
            <p>Don't have an account?</p>
            <a href="regist.jsp">Sign up</a>
        </div>
    </div>
    <div>
    	<input type="hidden" name="msg" value="${requestScope.loginfail}">
    </div>

	<!-- scripts -->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

    <script type="text/javascript">
        $(function () {
            // bg switcher
            var msg=$("input[name='msg']").val();
            if(msg!="" && msg!=null){
            	$(".label-warning").text(msg).removeClass("hide");
            }
            
            });

    </script>
</body>
</html>