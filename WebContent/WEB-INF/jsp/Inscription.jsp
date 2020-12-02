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
		<title>INSCRIPTION</title>
	</head>
<body>
  		<!-- INCLUSION HEADER -->
    	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<div class="container-fluid"> 
		<main>
    
            <!-- FORMULAIRE D'INSCRIPTION -->
			<div class="row mt-5">
     		 	<div class="col-sm-10 col-md-10 col-lg-10 mx-auto">
       		 		<div class="card card-signin my-5">
          				<div class="card-body">
           					<h5 class="card-title text-center mt-3">Inscription</h5>
           					
            				<form class="form-row form-signin needs-validation" action="${pageContext.request.contextPath}/Inscription" method="POST">
              					<div class="form-label-group col-sm-10 col-md-10 col-lg-10 mx-auto mt-3">
                					<input type="text" id="pseudo" name="pseudo" class="form-control" placeholder="pseudo" maxlength="30" required autofocus>
                					<label for="pseudo">Pseudo</label>
              					</div>
              					
              					<div class="form-label-group col-sm-6 col-md-6 col-lg-6">
                					<input type="text" id="nom" name="nom" class="form-control" placeholder="nom" rmaxlength="30" equired autofocus>
                					<label for="nom">Nom</label>
                				</div>	
                				
              					<div class="form-label-group col-sm-6 col-md-6 col-lg-6">
                					<input type="text" id="prenom" name="prenom" class="form-control" placeholder="prenom" maxlength="30" required autofocus>
               					 	<label for="prenom">Prenom</label>
              					</div>
              					
              					<div class="form-label-group col-sm-6 col-md-6 col-lg-6">
                					<input type="email" id="email" name="email" class="form-control" placeholder="email" maxlength="30" required autofocus>
               					 	<label for="email">Email</label>
              					</div>
              					
              					<div class="form-label-group col-sm-6 col-md-6 col-lg-6">
                					<input type="text" id="telephone" name="telephone" class="form-control" placeholder="telephone" maxlength="10" required autofocus>
               					 	<label for="telephone">telephone</label>
              					</div>
              					
              					<div class="form-label-group  mx-auto col-sm-10 col-md-10 col-lg-10">
                					<input type="text" id="rue" name="rue" class="form-control" placeholder="rue" maxlength="30"  required autofocus>
                					<label for="rue">Adresse</label>
              					</div>
              					
              					<div class="form-label-group col-sm-6 col-md-6 col-lg-6">
                					<input type="text" id="code_postal" name="code_postal" class="form-control" placeholder="code_postal"maxlength="5"  required autofocus>
               					 	<label for="code_postal">Code Postal</label>
              					</div>
              					
								<div class="form-label-group col-sm-6 col-md-6 col-lg-6">
                					<input type="text" id="ville" name="ville" class="form-control" placeholder="ville"maxlength="30"  required autofocus>
               					 	<label for="ville">ville</label>
              					</div>
              					
              					<div class="form-label-group  mx-auto col-sm-10 col-md-10 col-lg-10">
                					<input type="password" id="mot_de_passe" name="mot_de_passe" class="form-control" placeholder="mot_de_passe" maxlength="50" required>
                					<label for="mot_de_passe">Mot de Passe</label>
             					 </div>
             					 
             					 <div class="form-label-group  mx-auto col-sm-10 col-md-10 col-lg-10">
                					<input type="password" id="confirm_mdp" name="mot_de_passe" class="form-control" placeholder="mot_de_passe"maxlength="50" required>
                					<label for="confirm_mdp">Confirmation mdp</label>
             					 </div>
             					 
             					 <!--GESTION DES ERREURS-->
   							 	 <c:if test='${!empty err}'>
           								 <div class="d-flex alert-danger mx-auto col-sm-6 col-md-6 col-lg-6 text-center mt-5">
               								 <div class="col-3 p-2">
                 								 <p>error</p>
               								 </div>
               								 <ul class="col-9 list-unstyled p-2">
                    							<li>${err} </li>
                							</ul>
          							  </div>      
           						 </c:if>  
           						 
           						<!-- BOUTTONS -->
             					 <div class="form-label-group  mx-auto col-sm-10 col-md-10 col-lg-10 text-center mt-5">
              					<button class=" mx-auto btn btn-lg btn-primary btn-block text-uppercase col-sm-6 col-md-6 col-lg-6" type="submit">
              						 Créer compte
              					</button>
              					</div>
              					
              					<div class="form-label-group mb-4 mx-auto col-sm-10 col-md-10 col-lg-10 text-center">
              					<a class="mx-auto btn btn-lg btn-primary btn-block text-uppercase col-sm-6 col-md-6 col-lg-6" href="${pageContext.request.contextPath}/Accueil" role="button">
              						Annuler
              					</a>
              					</div>
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
 <script>
    // STARTER  BOOTSTRAP JS POUR VALIDATION
    (function() {
		'use strict';
		window.addEventListener('load', function() {
// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = document.getElementsByClassName('needs-validation');
// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
			form.addEventListener('submit', function(event) {
			if (form.checkValidity() === false) {
				event.preventDefault();
				event.stopPropagation();
			}
			form.classList.add('was-validated');
		}, false);
	});
}, false);
})();
 </script> 
</body>
</html>

