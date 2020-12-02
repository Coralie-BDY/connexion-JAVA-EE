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
		<title>HISTORIQUE</title>
	</head>
<body>
<!-- INCLUSION HEADER -->
    	<%@ include file="/WEB-INF/jspf/header.jspf" %>
    	
	<div class="container-fluid text-center"> 
	<main>
<!-- MES VENTES EN COURS -->
     	<div class="my-5 text-center">
        	<h2>Mes ventes en cours</h2>
        </div>
       
         <div class="text-center">	
 			<c:if test="${empty articleList }">
 				<p>Aucune vente en cours !</p>
 			</c:if>
 			
			<c:forEach var="article" items="${!empty requestScope.articleList}">
				<c:if test="${article.etat_vente == 'EC' }">
					<fieldset class="article-accueil">
		                <legend><strong>${article.nom_article}</strong></legend>
		                <div class="flex">
		                    <img src="..." alt="..." class="...">
		                    <ul class="liste-article-accueil">
		                        <li>Vendeur : ${sessionScope.thisUser.pseudo}</li>
		                        <li>Prix actuel : ${article.prix_vente}</li>
		                        <li>Fin de l'enchère le 
					           <%--      <fmt:parseDate value="${article.date_fin_enchere}" pattern="yyyy-MM-dd'T'HH:mm" var="dateFinEnchere"/>
									<fmt:formatDate value="${dateFinEnchere}" pattern="dd/MM/yyyy � HH:mm" var="dateFinEnchere2" />
									${dateFinEnchere2}		 --%>	     
		                        </li>
		                    </ul>
		                </div>
		                <div class="flex bouton">	
		                    <a class="lien" href="${pageContext.request.contextPath}/Article?noArticle=${article.no_article}" title="Voir le d�tail">Voir le détail</a>
		                </div>
		            </fieldset>
		          </c:if>
			</c:forEach>
		</div>
			 <!-- MES VENTES PLANIFIEES -->
		<div class="div-titre">
			<h2>Mes ventes planifiées</h2>
		</div>
		
        <div class="contenant-articles">	
			<c:forEach var="article" items="${!empty requestScope.articleList}">
				<c:if test="${article.etat_vente == 'CR' }">
					<fieldset class="article-accueil">
		                <legend><strong>${article.nom_article}</strong></legend>
		                <div class="flex">
		                    <img src="..." alt="..." class="...">
		                    <ul class="liste-article-accueil">
		                        <li>Vendeur : moi</li>
		                        <li>Prix : ${article.prix_vente}</li>
		                        <li>Début de l'enchère le 
					             <%--    <fmt:parseDate value="${article.date_debut_enchere}" pattern="yyyy-MM-dd HH:mm" var="dateDebutEnchere" />
									<fmt:formatDate value="${dateDebutEnchere}" pattern="dd/MM/yyyy HH:mm" var="dateDebutEnchere2"/>
									${dateDebutEnchere2}	 --%>		     
		                        </li>
		                    </ul>
		                </div>
		                <div class="flex bouton">	
		                    <a class="lien" href="${pageContext.request.contextPath}/Article?noArticle=${article.noArticle}" title="Voir le détail">Voir le détail</a>
		                </div>
		            </fieldset>
		        </c:if>
			</c:forEach>
		</div>
        		 <!-- MES VENTES TERMINEES -->
        	<div class="div-titre">
        		<h2>Mes ventes terminées</h2>
        	</div>	 
            <div class="contenant-articles">
				<c:forEach var="article" items="${!empty requestScope.articleList}">
					<c:if test="${article.etat_vente == 'VD' || ArticleVendu.etat_vente == 'RT' }">
					<fieldset class="article-accueil">
		                <legend><strong>${article.nom_article}</strong></legend>
		                <div class="flex">
		                    <img src="..." alt="..." class="...">
		                    <ul class="liste-article-accueil">
		                        <li>Vendeur : moi</li>
		                        <li>Prix actuel : ${article.prix_vente}</li>
		                        <li>Fin de l'enchère le 
					            <%--     <fmt:parseDate value="${article.date_fin_enchere}" pattern="yyyy-MM-dd HH:mm" var="dateFinEnchere"/>
									<fmt:formatDate value="${date_fin_enchere}" pattern="dd/MM/yyyy HH:mm" var="dateFinEnchere2"/>
									${dateFinEnchere2}	 --%>		     
		                        </li>
		                    </ul>
		                </div>
		                <div class="flex bouton">	
		                    <a class="lien" href="${pageContext.request.contextPath}/AfficheArticle?noArticle=${article.noArticle}" title="Voir le d�tail">Voir le d�tail</a>
		                </div>
		            </fieldset>
		         </c:if>
			</c:forEach>
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