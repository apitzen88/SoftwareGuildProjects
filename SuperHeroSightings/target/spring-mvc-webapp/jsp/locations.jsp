<%-- 
    Document   : locations
    Created on : Oct 25, 2017, 2:09:47 PM
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
            table {
                max-width: 100%;
            }
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
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperHeroesPage">SuperHeroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">
                    <h2>Locations</h2>
                    <table id="locationsTable" class="table table-hover">
                        <tr>
                            <th width="20%">City</th>
                            <th width="20%">Location</th>
                            <th width="30%">Description</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="10%>"></th>
                        </tr>
                        <c:forEach var="currentLocation" items="${locations}">

                            <tr>

                                <td>
                                    <c:out value="${currentLocation.city}"/>
                                </td>
                                <td>
                                    <c:out value="${currentLocation.locationName}"/>

                                </td>
                                <td>
                                    <c:out value="${currentLocation.description}"/>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-default">Info</button>
                                        <div class="dropdown-content">
                                            <p>
                                                Address:<br/>
                                                <c:out value="${currentLocation.address}"/>
                                            </p>
                                            <p>
                                                Latitude: <c:out value="${currentLocation.latitude}"/>
                                            </p>
                                            <p>
                                                Longitude: <c:out value="${currentLocation.longitude}"/>
                                            </p>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <a href="displayEditLocationForm?locationId=${currentLocation.locationId}">
                                        <button class="btn btn-default">Edit</button>
                                    </a>  
                                    </div>
                                </td>
                                <td>
                                    <a href="deleteLocation?locationId=${currentLocation.locationId}">
                                        <button class="btn btn-default">Delete</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="col-md-1"></div>

                <div class="col-md-5">
                    <h2>Add Location</h2>
                    <hr/>

                    <form class="form-horizontal"
                          role="form"
                          method="POST"
                          action="createLocation">

                        <div class="form-group">
                            <label for="locationName"
                                   class="col-md-4 control-label">Location Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="locationName" placeholder="Location Name" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="description"
                                   class="col-md-4 control-label">Description:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="description" placeholder="Description" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="address"
                                   class="col-md-4 control-label">Address:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="address" placeholder="Address" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="city"
                                   class="col-md-4 control-label">City:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="city" placeholder="City"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="latitude"
                                   class="col-md-4 control-label">Latitude:
                            </label>
                            <div class="col-md-8">
                                <input type="number" 
                                       min="000.000000"
                                       max="999.999999"
                                       step="000.000001"
                                       class="form-control" 
                                       name="latitude" placeholder="000.000000" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="longitude"
                                   class="col-md-4 control-label">Longitude:
                            </label>
                            <div class="col-md-8">
                                <input type="number" 
                                       min="000.000000"
                                       max="999.999999"
                                       step="000.000001"
                                       class="form-control" 
                                       name="longitude" placeholder="000.000000" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" 
                                       class="btn btn-default" 
                                       value="Add Location"/>
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
