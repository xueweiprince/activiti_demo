<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Detail Admin - Sign up</title>
    
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
    <link rel="stylesheet" href="css/compiled/signup.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
    <div class="header">
        <a href="index.html">
            <img src="img/logo4.png" class="logo" />
        </a>
    </div>
    <div class="row-fluid login-wrapper">
        <div class="box">
            <div class="content-wrap">
                <h6>Sign Up</h6>
                <form action="activiti/systemacount/regist.do" method="post">
                    <input class="span12" type="text" name="username" check-type="mail required" placeholder="E-mail address" />
                    <input class="span12" type="password" id="pw1" name="password" placeholder="Password" />
                    <input class="span12" type="password" name="confirmpassword" placeholder="Confirm Password" />
                    <label class="label label-warning hide" for="pw1" style="margin-bottom: -30px;margin-top:10px">两次密码输入不一致</label>
                </form>
                <div class="action">
                    <a class="btn-glow primary signup" onclick="commit()">Sign up</a>
                </div>                
            </div>
        </div>

        <div class="span4 already">
            <p>Already have an account?</p>
            <a href="login.jsp">Sign in</a>
        </div>
    </div>

	<!-- scripts -->
    <script src="js/jquery-1.9.1.min.js"></script>
    <script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
    
    <script type="text/javascript">
    
    	function commit(){
    		debugger;
    		var username=$("input[name='username']").val();
    		var pw=$("input[name='password']").val();
    		var cpw=$("input[name='confirmpassword']").val();
    		if(username==""||pw==""||cpw==""){
    			$(".content-wrap .label").text("输入框不能为空")
    			$(".content-wrap .label").removeClass("hide");
    			return false;
    		}
    		if(pw===cpw){
    			$(".content-wrap .label").addClass("hide");
    			$(".content-wrap form")[0].submit();
    		}else{
    			$(".content-wrap .label").text("两次输入的密码不一致")
    			$(".content-wrap .label").removeClass("hide");
    			return false;
    		}
    	}
    
    </script>
    
</body>