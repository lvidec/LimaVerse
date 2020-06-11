<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel='icon' href='images/loginImages/coffee.png' type='image/x-icon' />
<meta charset="ISO-8859-1">
<title>Login</title>
</head>

<style>

body{
    margin: 0;
    padding: 0;
    background: url(images/loginImages/pic1.jpg);
    background-size: cover;
    background-position: center;
    font-family: sans-serif;
}

.loginbox{
    width: 420px;
    height: 470px;
    background: #000;
    color: #fff;
    top: 50%;
    left: 50%;
    position: absolute;
    transform: translate(-50%,-50%);
    box-sizing: border-box;
    padding: 70px 30px;
}

.avatar{
    width: 100px;
    height: 100px;
    border-radius: 50%;
    position: absolute;
    top: -50px;
    left: calc(50% - 50px);
}

h1{
    margin: 0;
    padding: 0 0 20px;
    text-align: center;
    font-size: 22px;
}

.loginbox p{
    margin: 0;
    padding: 0;
    font-weight: bold;
}

.loginbox input{
    width: 100%;
    margin-bottom: 20px;
}

.loginbox input[type="text"], input[type="password"], input[type = "email"]
{
    border: none;
    border-bottom: 1px solid #fff;
    background: transparent;
    outline: none;
    height: 40px;
    color: #fff;
    font-size: 16px;
}
.loginbox input[type="submit"]
{
    border: none;
    outline: none;
    height: 40px;
    background: #fb2525;
    color: #fff;
    font-size: 18px;
    border-radius: 20px;
}
.loginbox input[type="submit"]:hover
{
    cursor: pointer;
    background: #ffc107;
    color: #000;
}
.loginbox a{
    text-decoration: none;
    font-size: 12px;
    line-height: 20px;
    color: darkgrey;
}

.loginbox a:hover
{
    color: #ffc107;
}

span{
	color: #ffc107;
	align: center;
	height: 100px;
}		


</style>


<body>


    <div class = "loginbox">
        <img src="images/loginImages/avatar.png" class="avatar">
        <h1>Login</h1>
		<form method = "post" action = "getDetails">
			
			<label for="cemail"> Email: </label> <br>
			<input type = "email" name = "cemail" placeholder = "Enter Email">	
			
			<label for="cpassword"> Password: </label> <br>  
			<input type = "password" name = "cpassword" placeholder = "Enter Password">

			<input class = "button" type = "submit" value = "Create Account">
			
			<a href="#">Lost your password?</a><br>
            <a href="#">Don't have an account?</a>

        </form>
    </div>
    
    
    <!-- 
    <div align = "center">
		<h2>Customer Register Form: </h2>
		<form method = "post" action = "getDetails">
<%-- 			<p>ID:          <input type = "text" name = "cid"> </p>     --%>
			
			<p>Email: 		<input type = "email" name = "cemail"> </p>
			<p>Password:    <input type = "password" name = "cpassword"> </p>
			
			<input type = "submit" value = "Submit">
        </form>
    </div>
 -->


</body>
</html>





<%--
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

    <div align = "center">
		<h2>Customer Register Form</h2>
		<form method = "post" action = "details">
			<p>ID:        <input type = "text" name = "cid"> </p>
			<p>Username:  <input type = "text" name = "cname"> </p>
			<p>Email:     <input type = "text" name = "cemail"> </p>
			<p>Password:  <input type = "text" name = "cpassword"> </p>
			<input type = "submit" value = "Submit">
        </form>
    </div>

</body>
</html>

--%>
