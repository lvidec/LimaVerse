<%@page import="com.WebApp.Login.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang = "en" xmlns:th="http://www.thymeleaf.org/">
<head>
<link rel='icon' href='images/loginImages/coffee.png' type='image/x-icon' />
<meta charset="ISO-8859-1">
<title>Create Acc</title>
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
    height: 560px;
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
        <h1>Create Account</h1>
		<form method = "post" action = "createAcc">
		

			<label for="cname"> Username: </label> <br> 
			<input type = "text" class = "form-control" name = "cname" placeholder = "Enter Username">
			<span class = "error"> ${nameError} </span> <br><br>
			
			<label for="cemail"> Email: </label> <br>
			<input type = "email" name = "cemail" placeholder = "Enter Email">	
			<span class = "error"> ${emailError} </span> <br><br>

			<label for="cpassword"> Password: </label> <br>  
			<input type = "password" name = "cpassword" placeholder = "Enter Password">
			<span class = "error"> ${passwordError} </span> <br><br>

			<input class = "button" type = "submit" value = "Create Account">

        </form>
    </div>

    

</body>
</html>




<%-- 

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<div align = "center">
		<h1>Create an Account:</h1>
		<form method = "post" action = "createAcc">
			<p>Email:     <input type = "text"> </p>
			<p>Password:  <input type = "text"> </p>
		</form>
	</div>
</body>
</html>

--%>