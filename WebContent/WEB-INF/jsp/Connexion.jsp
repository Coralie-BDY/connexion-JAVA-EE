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
		<title>CONNEXION</title>
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
           					<h5 class="card-title text-center">Connexion</h5>
            				<form class="form-signin"  action="${pageContext.request.contextPath}/Connexion" method="POST"">
              					<div class="form-label-group">
                					<input type="text" id="pseudo" name="pseudo" class="form-control" placeholder="pseudo" required autofocus>
               					 	<label for="pseudo">Pseudo</label>
              					</div>

              					<div class="form-label-group">
                					<input type="password" id="mdp" name="mot_de_passe" class="form-control" placeholder="Password" required>
                					<label for="mdp">Mot de Passe</label>
             					 </div>

              					<div class="custom-control custom-checkbox mb-3">
               	 					<input type="checkbox" name="remember" class="custom-control-input" id="customCheck1">
                					<label class="custom-control-label" for="customCheck1">Se souvenir de moi</label>
                					<a href="${pageContext.request.contextPath}/OublieMdp" class="ml-5 float-right">Mot de passe oublié</a>
                				</div>
                			 <!--GESTION DES ERREURS-->
                				<c:if test='${!empty err}'>
           							 <div class="d-flex alert-danger mx-auto col-sm-6 col-md-6 col-lg-6 text-center mt-5 mb-5">
                 						<p class="mx-auto justify-content-center">	<strong> ${err} </strong></p>
          							 </div>      
           					    </c:if>  
             					<!-- BOUTTONS -->
              					<button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Connexion</button>
              				
              					<p class="mt-5 text-center"> Pas de Compte ?<a href="${pageContext.request.contextPath}/Inscription"> Créer un compte</a>
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
<script src="js/script.js"></script>
</body>
</html>