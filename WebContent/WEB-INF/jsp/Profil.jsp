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
		<title>PROFIL</title>
	</head>
<body>
<!-- INCLUSION HEADER -->
    	<%@ include file="/WEB-INF/jspf/header.jspf" %>
    	
	<div class="container-fluid"> 
 
  		

    	<main>
    	  <!-- AFFICHAGE PROFIL -->
			<div class="row mt-5">
     		 	<div class="col-sm-6 col-md-6 col-lg-6 mx-auto">
       		 		<div class="card card-signin my-5">
          				<div class="card-body">
           					<h5 class="card-title text-center mt-3">Mon Profil</h5>
           					<input type="hidden" name="no_utilisateur" value="${Utilisateur.no_utilisateur}">
              					<div class="col-sm-10 col-md-10 col-lg-10 mx-auto mt-5 d-flex justify-content-center">
              						<img src="https://img.icons8.com/plasticine/48/000000/smiling.png"/>
              						 <p class="mt-3"><b>Pseudo : </b>  ${sessionScope.thisUser.pseudo}</p>
                				</div>
                				
              					<div class="col-sm-10 col-md-10 col-lg-10 mx-auto mt-3 d-flex justify-content-center">
              						<img src="https://img.icons8.com/doodle/48/000000/employee-card--v1.png"/>
              						<p class="mt-4"><b>Nom : </b> ${sessionScope.thisUser.nom}</p>
                				</div>	
                				
              					<div class="col-sm-10 col-md-10 col-lg-10 mx-auto mt-3 d-flex justify-content-center">
              						<img src="https://img.icons8.com/doodle/48/000000/employee-card--v1.png"/>
                					    <p class="mt-4"><b>Prénom : </b> ${sessionScope.thisUser.prenom}</p>
              					</div>
              					
              					<div class="col-sm-10 col-md-10 col-lg-10 mx-auto mt-3 d-flex justify-content-center">
              						<img src="https://img.icons8.com/doodle/48/000000/new-post.png"/>
                					<p class="mt-4"><b>Email : </b> ${sessionScope.thisUser.email}</p>
              					</div>
              					
              					<div class="col-sm-10 col-md-10 col-lg-10 mx-auto mt-3 d-flex justify-content-center">
              					<img src="https://img.icons8.com/dusk/48/000000/phone-not-being-used.png"/>
                					 <p class="mt-4"><b>Téléphone : </b> <span class="text-muted"> ${sessionScope.thisUser.telephone}</span></p>
              					</div>
              					
              					<div class="mx-auto col-sm-10 col-md-10 col-lg-10  d-flex justify-content-center">
              						<img src="https://img.icons8.com/plasticine/48/000000/address.png"/>
                					<p class="mt-4"><b>Adresse : </b> ${sessionScope.thisUser.rue}</p>
              					</div>
              					
              					<div class="col-sm-10 col-md-10 col-lg-10 mx-auto mt-3 d-flex justify-content-center">
              						<img src="https://img.icons8.com/officel/48/000000/mailbox-with-letter.png"/> 
                					<p class="mt-4"><b>Code postal : </b> ${sessionScope.thisUser.codePostal}</p>
              					</div>
              					
								<div class=" col-sm-10 col-md-10 col-lg-10 mx-auto mt-3 d-flex justify-content-center">
									<img src="https://img.icons8.com/dusk/48/000000/real-estate.png"/> 
                					<p class =" mt-4"><b>Ville : </b> ${sessionScope.thisUser.ville}</>
              					</div>
              					
           						<!-- BOUTTONS -->
           						<c:if test="${!empty sessionScope.thisUser}" >
             						<div class="row mt-5 col-sm-10 col-md-10 col-lg-10 mx-auto">
              							<a class="mx-auto btn btn-lg btn-primary btn-block text-uppercase col-sm-4 col-md-4 col-lg-4"
              								role="button" href="${pageContext.request.contextPath}/ModifierProfil">
              								Modifier
              							</a>
              						</div>
              					 </c:if>
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