<%-- 
    Document   : addPowersHero
    Created on : Oct 31, 2017, 3:49:48 PM
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
            <h1>Add/Remove Powers</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySuperHeroesPage">SuperHeroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                </ul>    
            </div>

            <div class="col-md-3"></div>

            <div class="col-md-6">

                <div class="row">

                    <form method="POST" action="addPower?heroId=${hero.heroId}">

                        <div id="hero">
                            <label value="${hero.heroId}">Add Powers for ${hero.heroName}</label>
                        </div>

                        <div class="form-group">
                            <div class="panel-group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse1">Add Powers</a>
                                        </h4>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <c:forEach var="currentPower" items="${available}">
                                                <div class="checkbox-inline">
                                                    <label class="checkbox-inline"><input type="checkbox" name="selectedPowers" 
                                                                  value="<c:out value="${currentPower.superPowerId}"/>">
                                                        <c:out value="${currentPower.description}"/>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div>
                            <a href="addPower?heroId=${hero.heroId}">
                                <button type="submit" class="btn btn-default">Add Powers</button>
                            </a>
                        </div>

                    </form>

                </div>


                <br/>


                <div class="row">

                    <form method="POST" action="removePower?heroId=${hero.heroId}">

                        <div id="hero">
                            <label value="${hero.heroId}">Remove Powers for ${hero.heroName}</label>
                        </div>

                        <div class="form-group">
                            <div class="panel-group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse2">Remove Powers</a>
                                        </h4>
                                    </div>
                                    <div id="collapse2" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <c:forEach var="currentPower" items="${heroPowers}">
                                                <div class="checkbox-inline">
                                                    <label class="checkbox-inline"><input type="checkbox" name="selectedRemovePowers" 
                                                                  value="<c:out value="${currentPower.superPowerId}"/>">
                                                        <c:out value="${currentPower.description}"/>
                                                    </label>
                                                </div>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div>
                            <a href="removePower?heroId=${hero.heroId}">
                                <button type="submit" class="btn btn-default">Remove Powers</button>
                            </a>
                        </div>

                    </form>

                </div>

                <br/>

                <div class="row">
                    <a href="displaySuperHeroesPage">
                        <button type="submit" class="btn btn-default">Go Back</button>
                    </a>
                </div>

                <div class="col-md-3"></div>

            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</html>
