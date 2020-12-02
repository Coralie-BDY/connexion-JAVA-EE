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
		<title>MODIFICATION VENTE</title>
	</head>
<body>
<!-- INCLUSION HEADER -->
    	<%@ include file="/WEB-INF/jspf/header.jspf" %>
    	
	<div class="container-fluid"> 
    	<main>
    	<!-- FORMULAIRE DE MISE EN VENTE -->
    	<div class="row mt-5">
     		 <div class="col-sm-10 col-md-10 col-lg-10 mx-auto">
       		 	<div class="card card-signin my-5">
    		
  				<div class="row no-gutters">
  					<div class="col-md-4">
      					<img src="images/Recherche.jpg" class="card-img" alt="image recherche">
    				</div>
    				<div class="col-md-8">
      					<div class="card-body">
        					<h5 class="card-title text-center"> MODIFICATION VENTE</h5>
   							<form  class="form-register needs-validation" novalidate action="${pageContext.request.contextPath}/ModifierVente" method="POST">
	            			 <input type="hidden" name="no_article" value="${article.no_article}"> 
	            			<fieldset class="fieldset-vente-article">
								<legend>Article</legend>  
	            				 
	              				<div class="row">
	                  				<div class="col-md-6 mb-3">
	                      				<label for="nom_article">Nom de l'article</label>
	                      				<input type="text" class="form-control" id="nom_article" name="nom_article" placeholder="Nom de votre article" maxlength="30" required value="${article.nom_article}">
	                      				<!-- <div class="invalid-feedback">
	                          				Ce champ est invalide !
	                      				</div> -->
	                  				</div>
	              				</div>
	              
	              				<div class="row">
	                  				<div class="col-md-12 mb-3">
	                      				<label for="description">Description</label>
	                     				<textarea class="form-control" id="description" name="description" placeholder="Quelques mots pour décrire votre produit (500 caractères maximum)." value="${article.description}" rows="4" cols="" maxlength="500" required></textarea>
	                      				<!-- <div class="invalid-feedback">
	                          				Ce champ est invalide !
	                      				</div> -->
	                  				</div>
	              				</div>
	              
	              				<div class="row">
	                   				<div class="col-md-6 mb-3">
	                          			<label for="no_categorie">Catégories</label>
	                         			<select class="form-control" id="no_categorie" name="no_categorie" value="${article.noCategorie}" required>
	                              			<option selected >Choisir une catégorie</option>
	                              			<option value="1">Informatique</option>
	                              			<option value="2">Meubles</option>
	                              			<option value="3">Vêtement</option>
	                              			<option value="4">Sport Loisirs</option>
	                          			</select>
	                      			</div>
	                      			
	                  				<div class="col-md-6 mb-3">
	                      				<label for="prix_initial">Prix initial</label>
	                       				<input type="number" class="form-control" id="prix_initial" name="prix_initial" placeholder="" value="${article.prix_initial}" maxlength="20" required>
	                       			<!-- <div class="invalid-feedback">
	                          			 Ce champ est invalide !
	                       			</div> -->
	                   				</div>
	                   			
	                   				<div class="row col-md-6 ml-1 mb-3">
	                   					<label for="image">Images</label>
	                   					<div class="input-group ">
 											<div class="input-group-prepend">
    											<span class="input-group-text" id="inputGroupFileAddon01">Uploader</span>
  											</div>
  											<div class="custom-file">
    											<input type="file" class="custom-file-input" id="inputGroupFile01" name="image" value="${article.images}" aria-describedby="inputGroupFileAddon01">
   												<label class="custom-file-label" for="inputGroupFile01">votre image</label>
											</div>
										</div>
									</div>
	              				</div>
	            			</fieldset> 
	            			
                			<fieldset class="fieldset-vente-article">
							<legend>Lancement de l'enchère</legend>  	 
	                		<div class="row">  
	                  			<div class="col-md-6 mb-3">
	                      			<label for="date_debut_encheres">Date d'ouverture de l'enchère</label>
	                      			<input type="date" class="form-control" id="date_debut_encheres" name="date_debut_encheres" placeholder="" value="${requestScope.date}"  required>                       
					      			<p id="test"></p> 
					      			<!-- <div class="invalid-feedback">
					         			 Ce champ est invalide !
					     			</div> -->
					  			</div>
	 
								<div class="col-md-6 mb-3">
	      							<label for="dateheuredebut">Heure d'ouverture de l'enchère</label>
	     							<input type="time" class="form-control" id="dateheuredebut" name="dateheuredebut" placeholder="" value="${requestScope.time}" required>
	                    			<div class="invalid-feedback">
	                        			Ce champ est invalide !
	                   				</div>
	                			</div>
	            
	                  
	                 			<div class="col-md-6 mb-3">
	                          		<label for="duree-select">Durée de l'enchère</label>
	                         		<select class="form-control" id="duree-select" name="duree">
	                              		<option value="3">3 jours</option>
	                              		<option value="5">5 jours</option>
	                              		<option selected value="7">7 jours</option>
	                              		<option value="10">10 jours</option>
	                          		</select>
                      			</div>
	               			</div>
	      				</fieldset>  
	
						<fieldset class="fieldset-vente-article">
							<legend>Adresse de retrait</legend>
							<div class="row">
					    		<div class="col-md-6 mb-3">
			                     	<label for="street">Adresse</label>
			                      	<input type="text" class="form-control" id="rue" name="rue" placeholder="Adresse" value="${sessionScope.thisUser.rue }" maxlength="30" required>
			               			<!-- <div class="invalid-feedback">
			                   			Ce champ est invalide !
			               			</div> -->
			           			</div>
				
								<div class="col-md-6 mb-3">
						        	<label for="code_postal">Code postal</label>
						            <input type="text" class="form-control" id="code_postal" name="code_postal" placeholder="code postal"value="${sessionScope.thisUser.codePostal }" required>
						    		<!-- <div class="invalid-feedback">
						        		Ce champ est invalide !
						    		</div> -->
								</div>
				
								<div class="col-md-6 mb-3">
					   				<label for="city">Ville</label>
					    			<input type="text" class="form-control" id="ville" name="ville" placeholder="ville" maxlength="30" value="${sessionScope.thisUser.ville }" required>
		                    		<!-- <div class="invalid-feedback">
		                        		Ce champ est invalide !
		                    		</div> -->
		               			 </div>   
			        		</div>          
			     		</fieldset>    
						 <!--GESTION DES ERREURS-->
   						 <c:if test='${!empty err}'>
           					<div class="d-flex alert-danger mx-auto col-sm-6 col-md-6 col-lg-6 text-center mt-3 mb-4">
               					<div class="col-3 p-2">
                 					<p>error</p>
               					</div>
               					<ul class="col-9 list-unstyled p-2">
                    				<li>${err} </li>
                				</ul>
          					</div>      
           				</c:if>  
						<div class="form-label-group  mx-auto">
              			<button class="mx-auto btn btn-lg btn-primary btn-block text-uppercase col-sm-4 col-md-4 col-lg-4">
              				Modifier mon enchère
              			</button>
	    				</div>
	    			</form>     					
      				</div>
    			</div>
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