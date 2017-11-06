<%-- 
    Document   : sightings
    Created on : Oct 25, 2017, 2:10:51 PM
    Author     : apitz_000
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f9f9f9;
                min-width: 160px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                padding: 12px 16px;
                z-index: 1;
            }

            .dropdown:hover .dropdown-content {
                display: block;
            }

            form {
                width: 80%;
                margin: 0 auto;
            }

            label, input {
                display: inline-block;
            }

            label {
                width: 30%;
                text-align: right;
            }

            label + input {
                width: 30%;
                margin: 0 30% 0 4%;
            }
            input + input {
                float: right;
            }
/*            table{
                table-layout: fixed;
            }
            button {
                max-width: 100%;
                display: inline-block;
                word-wrap: break-word;
            }*/
        </style>
        <title>Locations</title>
    </head>
    <body>
        <div class="container">
            <h1>SuperHero Sightings</h1>
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


            <div class="row">

                <div class="col-lg-6">

                    <h2>Sightings</h2>
                    <table id="sightingsTable" class="table table-hover">
                        <tr>
                            <th width="10%">Date</th>
                            <th width="30%">Location</th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightings}">
                            <tr>

                                <td>
                                    <c:out value="${currentSighting.date}"/>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <c:out value="${currentSighting.location.locationName}"/>
                                        <div class="dropdown-content">
                                            <p>
                                                Description: <br/>
                                                <c:out value="${currentSighting.location.description}"/><br/>
                                            </p>
                                            <p>
                                                Address: <br/>
                                                <c:out value="${currentSighting.location.address}"/><br/>
                                            </p>
                                            <p>
                                                City: <br/>
                                                <c:out value="${currentSighting.location.city}"/><br/>
                                            </p>
                                            <p>
                                                Latitude: <br/>
                                                <c:out value="${currentSighting.location.latitude}"/><br/>
                                            </p>
                                            <p>
                                                Longitude: <br/>
                                                <c:out value="${currentSighting.location.longitude}"/><br/>
                                            </p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-default">Heroes</button>
                                        <div class="dropdown-content">
                                            <p>
                                                <c:forEach var="hero" items="${currentSighting.heroes}">
                                                    <c:out value="${hero.heroName}"></c:out><br/>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a href="displayAddHeroesSighting?sightingId=${currentSighting.sightingId}">
                                        <button class="btn btn-default">Add/Remove Heroes</button>
                                    </a>
                                </td>
                                <td>
                                    <a href="displayEditSightingForm?sightingId=${currentSighting.sightingId}">
                                        <button class="btn btn-default">Edit Info</button>
                                    </a>  
                                </td>
                                <td width="10%">
                                    <a href="deleteSighting?sightingId=${currentSighting.sightingId}">
                                        <button class="btn btn-default">Delete</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="col-md-1"></div>

                <div class="col-md-5">

                    <h2>Add Sighting</h2>
                    <hr/>

                    <form class="form-horizontal"
                          id="addSighting"
                          role="form"
                          method="POST"
                          action="createSighting">

                        <div class="form-group">
                            <label for="date"
                                   class="col-md-4 control-label">Date:
                            </label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" 
                                       placeholder="Date" id="dateate" name="date" required> 
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="locations">Select Location:</label>
                            <select class="form-control" id="location" name="location">
                                <c:forEach var="currentLocation" items="${locations}">
                                    <option value="${currentLocation.locationId}"><c:out value="${currentLocation.locationName}"/>,  
                                        <c:out value="${currentLocation.description}"/>, <c:out value="${currentLocation.city}"/></option>
                                    </c:forEach>
                            </select>
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
                                            <c:forEach var="currentHero" items="${heroes}">
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

                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" 
                                       class="btn btn-default" 
                                       value="Add Sighting"/>
                            </div>
                        </div>

                    </form>

                </div>

            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>       
    </body>
</html>
