<%-- 
    Document   : addHeroesSighting
    Created on : Nov 4, 2017, 6:49:11 PM
    Author     : apitz_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">

        <title>Add/Remove Heroes</title>
    </head>
    <body>
        <div class="container">
            <h1>Add/Remove Heroes</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperHeroesPage">SuperHeroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                </ul>    
            </div>

            <div class="col-md-3"></div>

            <div class="col-md-6">

                <div class="row">

                    <form method="POST" action="addHeroSighting?sightingId=${sighting.sightingId}">

                        <div id="org">
                            <label value="${sighting.sightingId}">
                                Add heroes to Sighting:<br/>
                                ${sighting.location.locationName}, ${sighting.location.description}, ${sighting.location.city} on ${sighting.date}</label>
                        </div>

                        <div class="form-group">
                            <div class="panel-group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse1">Add heroes</a>
                                        </h4>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <c:forEach var="currentHero" items="${available}">
                                                <div class="checkbox-inline">
                                                    <label><input type="checkbox" name="selectedHeroes" 
                                                                  value="<c:out value="${currentHero.heroId}"/>">
                                                        <c:out value="${currentHero.heroName}"/>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div>
                            <a href="addHeroSighting?sightingId=${sighting.sightingId}">
                                <button type="submit" class="btn btn-default">Add Heroes</button>
                            </a>
                        </div>

                    </form>

                </div>


                <br/>


                <div class="row">

                    <form method="POST" action="removeHeroSighting?sightingId=${sighting.sightingId}">

                        <div id="org">
                            <label value="${sighting.sightingId}">
                                Remove heroes from Sighting:<br/> 
                                ${sighting.location.locationName}, ${sighting.location.description}, ${sighting.location.city} on ${sighting.date}</label>
                        </div>

                        <div class="form-group">
                            <div class="panel-group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse2">Remove Heroes</a>
                                        </h4>
                                    </div>
                                    <div id="collapse2" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <c:forEach var="currentHero" items="${sightingHeroes}">
                                                <div class="checkbox-inline">
                                                    <label><input type="checkbox" name="selectedRemoveHeroes" 
                                                                  value="<c:out value="${currentHero.heroId}"/>">
                                                        <c:out value="${currentHero.heroName}"/>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div>
                            <a href="removeHeroSighting?sightingId=${sighting.sightingId}">
                                <button type="submit" class="btn btn-default">Remove Heroes</button>
                            </a>
                        </div>

                    </form>

                </div>

                <br/>

                <div class="row">
                    <a href="displaySightingsPage">
                        <button type="submit" class="btn btn-default">Go Back</button>
                    </a>
                </div>

                <div class="col-md-3"></div>

            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
