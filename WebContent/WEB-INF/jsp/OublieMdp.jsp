<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<!--  RESPONSIVE META TAG  -->
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<!--  SCRIPT CSS BOOTSRAP  -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
	 		 integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
		<link href="css/style.css" rel="stylesheet">
		<title>RECUPERATION MOT DE PASSE</title>
	</head>
<body>
  		<!-- INCLUSION HEADER -->
    	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<div class="container-fluid"> 

    	
    	<main>
    	<!-- FORMULAIRE DE CONNEXION -->
   			 <div class="row mt-5">
     		 	<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
       		 		<div class="card card-signin my-5">
          				<div class="card-body">
           					<h5 class="card-title text-center">Récupération mot de passe</h5>
            				<form class="form-signin"  action="${pageContext.request.contextPath}/Accueil" method="POST">
              					<div class="form-label-group mb-5 ">
                					<input type="email" id="email" name="email" class="form-control" placeholder="email" maxlength="30" value="" required autofocus>
               					 	<label for="email">Email</label>
              					</div>

              					<div class="form-text text-muted mb-3 ml-3">
                					En cliquant sur "Réinitaliser le mot de passe" nous vous enverrons un lien de réinitialisation.
              					 </div>

             					<!-- BOUTTONS -->
              					<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Réinitaliser le mot de passe</button>
            				</form>
         				 </div>
        			</div>
     		 	 </div>
    		 </div>
    	</main>	
   </div>
    	
    	<!-- INCLUSION FOOTER -->
		<%@include file="/WEB-INF/jspf/footer.jspf" %>
  
  


<!--  SCRIPT JS BOOTSRAP  -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" 
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
		
</body>
</html>