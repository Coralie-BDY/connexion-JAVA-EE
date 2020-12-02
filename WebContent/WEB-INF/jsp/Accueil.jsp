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
		<title>ACCUEIL</title>
	</head>
<body>
<!-- INCLUSION HEADER -->
    	<%@ include file="/WEB-INF/jspf/header.jspf" %>
    	
	<div class="container -fluid"> 
  		
  	<header class=" bg-info  text-white my-5">
   		 <div class="text-center my-5">
      		<h1>Bienvenue sur notre site d'Enchères</h1>
      			<p class="lead">les objets sont nos amis, apprenons à les recycler</p>
    	</div>
 	 </header>
  
    	<main>
    	<form action="${pageContext.request.contextPath}/accueil" method="POST">
         	<fieldset  class="fieldset-filtres mb-5"> 
				<div class="flexbox-filtres">
                    <input type="text" class="element-filtre" id="filter-input" name="nom_article" placeholder="articles contenant...">
						<label class="element-filtre ml-2" id="categories-label" for="">Catégories</label>
						
						<select class="element-filtre" id="categories-select" name="no_categorie">
						    <option selected value="0">Toutes</option>
						    <option value="1">Informatique</option>
						    <option value="2">Meubles</option>
						    <option value="3">Vétement</option>
						    <option  value="4">Sport Loisirs</option>
						</select>
	
	               		<button class="btn btn-primary ml-2" type="submit" title="rechercher">
	                		Rechercher
	                	</button>
               	</div>
           </fieldset>
       </form>
       
            <div class="row mt5 col-md-12 mb-5 d-flex justify-content-between ">
			<div class="card col-md-5 col-lg-5" style="width: 18rem;">
  				<img class="card-img-top img-thumbnail" src="images/vide.jpg" alt="Card image cap">
 				 <div class="card-body">
 				  	<h5 class="card-title">Card title</h5>
 				   	<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
   				 	<a href="#" class="btn btn-primary">Voir le détail</a>
  			</div>
			</div>
			<div class="card col-md-5 col-lg-5 ml-3" style="width: 18rem;">
  				<img class="card-img-top img-thumbnail" src="images/vide.jpg"  alt="Card image cap">
 				 <div class="card-body">
 				  	<h5 class="card-title">Card title</h5>
 				   	<p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
   				 	<a href="#" class="btn btn-primary">Voir le détail</a>
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