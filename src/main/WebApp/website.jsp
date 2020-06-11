<!DOCTYPE html>
<%@page import="com.WebApp.Login.Headphone"%>
<%@page import="java.io.Console"%>
<%@page import="com.WebApp.Login.LoginController"%>
<%@page import="com.WebApp.Login.Laptop"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html lang="en">
<head>
  <title>LimaVerse</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link rel='icon' href='images/loginImages/coffee.png' type='image/x-icon' />
  
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  
  <link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel = "stylesheet">
  <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
  <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  
  <style>
    
    .navbar {
      margin-bottom: 50px;
      border-radius: 0;
    }
    
     .jumbotron {
      margin-bottom: 0;
    }
    
    header{
    	background: url(images/loginImages/pic1.jpg);
    	background-size: cover;
   	    background-position: center;
    }
   
    footer {
      background-color: #f2f2f2;
      background: url(images/loginImages/pic1.jpg);
      padding: 55px;
    }
   
   	.description{
   		margin-left: 25px;
   	}
   	
   	p{
   		font-size: 18px;
   	}
    
    .bg-grey{
    	background-color: grey;
		height: 40px;
		text-align: center;
		color: white;
    }
    
    .bg-grey p{
    	padding-top: 8px;
    }
    
    .btn-buy{
    	background-color: rgb(30, 30, 30);
		height: 40px;
		text-align: center;
		color: white;
    }
    
    .btn-navigation {
    	background-color: rgb(34, 34, 34);
		height: 50px;
		text-align: center;
		font-size: 14px;
		color: gray;
		border: hidden;
    }
    
    .btn-navigation:hover {
    	color: white;
    }
    
    .btn-filter{
    	background-color: orange;
		padding: 10px;
		border-radius: 10px;
		text-align: center;
		font-size: 14px;
		color: black;
		float: center;
    }
    
	#slider{
		margin-left: 10px;
		margin-right: 10px;
	}
    
    
  </style>
  
   <script>
         $(function() {
            $( "#slider" ).slider({
               range:true,
               min: 0,
               max: 4200,
               values: [ 0, 4200 ],
               slide: function( event, ui ) {
            	  document.getElementById("button").value = ui.values[1];
                  $( "#price" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
               }
            });
            $( "#price" ).val( "$" + $( "#slider" ).slider( "values", 0 ) +
               " - $" + $( "#slider" ).slider( "values", 1 ) );
         });
   </script>
  
  
</head>

<body>

<header class="jumbotron">
  <div class="container text-center">
    <h1> LimaVerse </h1>      
    <p>Feel your inner senses</p>
  </div>
</header>

<nav class="navbar navbar-inverse">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active">
       		<form action = "/" method = "get">
       			<button class = "btn-navigation" name = "showAll" value = "1"> PRODUCTS </button>
        	</form>
        </li>
       	<li class="active">
       		<form action = "/" method = "get">
       			<button class = "btn-navigation" name = "showLaptops" value = "1"> LAPTOPS </button>
        	</form>
        </li>
        <li class="active">
       		<form action = "/" method = "get">
        		<button class = "btn-navigation" name = "showTvs" value = "1"> TVs </button>
       		</form>
        </li>
        <li class="active">
       		<form action = "/" method = "get">
        		<button class = "btn-navigation" name = "showHeadphones" value = "1"> HEADPHONES </button>
      		</form>
      	</li>
      </ul>
      
      <ul class="nav navbar-nav navbar-right">
        
        <% if( LoginController.isRegistered == true){  %>
        	<li>
        	<a href="#">
        		<span class="glyphicon glyphicon-user"></span> Your Account
       		</a>
    	</li>
	<%  }  
        
        else{  %>
        	<li>
        		<a href="${pageContext.request.contextPath}/login">
        			<span class="glyphicon glyphicon-user"></span> Login 
       			</a>
    		</li>
	<%  }  %>
	   
        <li>
        	<a href="${pageContext.request.contextPath}/bucket">
        		<span class="glyphicon glyphicon-shopping-cart"></span> (${numOfItemsBucket}) Cart
        	</a>
        </li>
        
        <li>
       		<a href="${pageContext.request.contextPath}/create">
       			<span class="glyphicon glyphicon-user"></span> Create Account 
      		</a>
   		</li>
      </ul>
    </div>
  </div>
</nav>

<div class = "container centerContent">
	
	<p align = center>
	    <label for = "price" style = "margin-left: 10px;">   Price range: </label>
	    <input type = "text" id = "price" style = "border:0; color:#b9cd6d; font-weight:bold;">
	</p>
	
	<div class = "row">
		<div class = "col-sm-3"></div>
		<div id = "slider" class = "col-sm-6"></div>
		<div class = "col-sm-3"></div>
	</div>


	<br>

	<form action = "/" method = "get">
		<button id = "button" class = "btn-filter center-block" name = "filter"> Filter </button>
	</form>

<br><br>


<%  int i = 1;
	
%>
<c:forEach items = "${laptops}" var = "laptop" >


<%  if( i % 3 == 0){ %>
    	<div class="container-fluid">
    	<div class="row"> 
    	
<%   }   %>


     <div class="col-sm-12 col-md-12 col-lg-4">
      <div class="panel panel-primary">
        <div class="bg-grey"> 
        	<p> ${laptop.getPname()} -> $${laptop.getPprice()} </p> 
      	</div>
        <div class="panel-body">
        	<div class = "col-xs-5">
	    	    <img src="${laptop.getPpicPath()}" width = "150" height = "150" alt="Image">
	        </div>
        	<div class = "col-xs-7">
	        	<p class = "description" align = "left"> ${laptop.getPdescription()} </p>
	        </div>
        </div>        
        <form action = "bucket" method = "get">
        	<button class="btn-buy" name = "laptopButton" value = "${laptop.getPid()}">Add to cart</button>
      	</form>
      </div>
    </div>
      
<%  if( i % 3 == 0 ){ %>
    	</div>
    	</div>
<%  }   %>

<% i++; %>

</c:forEach>


<c:forEach items = "${tvs}" var = "tv" >

	
<%  if( i % 3 == 0 ){ %>
    	<div class="container-fluid">
    	<div class="row"> 
<%   }   %>

     <div class="col-sm-12 col-md-12 col-lg-4">
      <div class="panel panel-primary">
        <div class="bg-grey"> 
        	<p> ${tv.getPname()} -> $${tv.getPprice()} </p> 
        </div>
         <div class="panel-body">
        	<div class = "col-xs-5">
	    	    <img src="${tv.getPpicPath()}" width = "150" height = "150" alt="Image">
	        </div>
        	<div class = "col-xs-7">
	        	<p class = "description" align = "left"> ${tv.getPdescription()} </p>
	        </div>
        </div>
<!--    <input type = "button" class="panel-footer" name = "tvButton" value = "true" onclick="">Add to cart>     -->  
		<form action = "bucket" method = "get">
        	<button class="btn-buy" name = "tvButton" value = "${tv.getPid()}" >Add to cart</button>
      	</form>
      </div>
    </div>
      
<%  if( i % 3 == 0 ){ %>
    	</div>
    	</div>
<%  }   %>

<% i++; %>

</c:forEach>



<c:forEach items = "${headphones}" var = "headphone" >

	
<%  if( i % 3 == 0 ){ %>
    	<div class="container-fluid">
    	<div class="row"> 
<%   }   %>

     <div class="col-sm-12 col-md-12 col-lg-4">
      <div class="panel panel-primary">
        <div class="bg-grey"> 
        	<p> ${headphone.getPname()} -> $${headphone.getPprice()} </p> 
        </div>
        <div class="panel-body">
        	<div class = "col-xs-5">
	    	    <img src="${headphone.getPpicPath()}" width = "150" height = "150" alt="Image">
	        </div>
        	<div class = "col-xs-7">
	        	<p class = "description" align = "left"> ${headphone.getPdescription()} </p>
	        </div>
        </div>
<!--    <button class="panel-footer" name = "headphoneButton">Add to cart</button>  -->
		<form action = "bucket" method = "get">
        	<button class="btn-buy" name = "headphoneButton" value = "${headphone.getPid()}" >Add to cart</button>
      	</form>
      </div>
    </div>
      
      
<%  if( i % 3 == 0 ){ %>
    	</div>
    	</div>
<%  }   %>


<% i++; %>

</c:forEach>


<br><br><br>

</div>

<footer class="container-fluid text-center">
  <p> LimaVerse Copyright </p>  
  <img alt="image" src="images/loginImages/coffee.png" height = "100px" width = "100">
</footer>

</body>
</html>