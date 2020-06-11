<!DOCTYPE html>
<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<html lang="en">
<head>
    <title>Customer Bucket</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel='icon' href='images/loginImages/coffee.png' type='image/x-icon' />
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	
	<style>
	
	header{
		background: url(images/loginImages/pic1.jpg);
    	background-size: cover;
   	    background-position: center;
	}
	
	.buttonAlign{
		float: right;
		margin-bottom: 10px;
	}
	
	.btn-website {
    	background-color: dodgerblue;
		padding: 20px;
		border-radius: 10px;
		text-align: center;
		font-size: 18px;
		color: black;
    }

	.btn-delete {
    	background-color: red;
		padding: 10px;
		border-radius: 7px;
		text-align: center;
		font-size: 18px;
		color: black;
		float: right;
    }

	</style>


</head>
<body>


    <header class="jumbotron"> 
        <p align = "center"> Customer bucket</p>
        <p align = "center">Total price: $${price} </p>
	    <div class = "buttonAlign">
	        <a href = "${pageContext.request.contextPath}/" class = "btn-website"> Website </a>
    	</div>
    	<br>
    </header>

    <div class="container mid">
    
   		
        <c:forEach items = "${bucketLaptops}" var = "item" >
        
        <div class="row jumbotron">
            <div class="col-12">
                <img align = "left" src="${item.getPpicPath()}" width="200" height="150" style = "margin-bottom: 20px">
                <p align = "center">${item.getPname()}</p>
                <p align = "center">$${item.getPprice()}</p>
                <form action = "bucket" method = "get">
       				<button class = "btn-delete" name = "delete" value = "${item.getPname()}"> Delete </button>
        		</form>
            </div>
        </div>

   		</c:forEach>
        
        <c:forEach items = "${bucketTvs}" var = "item" >
        
        <div class="row jumbotron">
            <div class="col-12">
                <img align = "left" src="${item.getPpicPath()}" width="200" height="150" style = "margin-bottom: 20px">
                <p align = "center">${item.getPname()}</p>
                <p align = "center">$${item.getPprice()}</p>
                <form action = "bucket" method = "get">
       				<button class = "btn-delete" name = "delete" value = "${item.getPname()}"> Delete </button>
        		</form>
            </div>
        </div>

   		</c:forEach>
    	
    	<c:forEach items = "${bucketHeadphones}" var = "item" >
        
        <div class="row jumbotron">
            <div class="col-12">
                <img align = "left" src="${item.getPpicPath()}" width="200" height="150" style = "margin-bottom: 20px">
                <p align = "center">${item.getPname()}</p>
                <p align = "center">$${item.getPprice()}</p>
                <form action = "bucket" method = "get">
       				<button class = "btn-delete" name = "delete" value = "${item.getPname()}"> Delete </button>
        		</form>
            </div>
        </div>

   		</c:forEach>
    	
    	
    </div>



    
</body>
</html>