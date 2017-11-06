<%-- 
    Document   : editSightingForm
    Created on : Nov 4, 2017, 4:01:34 PM
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
        <style>
            form {
                width: 30%;
                margin: 0 auto;
                float: left;
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
        </style>
        <title>Edit Location</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Sighting</h1>
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
            <div class="col-md-offset-0">
                <form class="form-horizontal"
                      id="addSighting"
                      role="form"
                      method="POST"
                      action="updateSighting?sightingId=${sighting.sightingId}">

                    <div class="form-group">
                        <label for="date"
                               class="col-md-4 control-label">Date:
                        </label>
                        <div class="col-md-8">
                            <input type="date" class="form-control" value="${sighting.date}"
                                   placeholder="Date" id="dateate" name="date"> 
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="locations">Select Location:</label>
                        <select class="form-control" id="location" name="location">
                            <c:forEach var="currentLocation" items="${locations}">
                                <option value="${currentLocation.locationId}" 
                                        <c:if test="${currentLocation.locationId == selectedLoc.locationId}"> selected </c:if>>
                                    <c:out value="${currentLocation.locationName}"/>, <c:out value="${currentLocation.description}"/>, 
                                    <c:out value="${currentLocation.city}"/></option>
                                </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-8">
                            <a href="updateSighting?sightingId=${sighting.sightingId}">
                                <input type="submit" 
                                       class="btn btn-default" 
                                       value="Update Sighting"/></a>
                        </div>
                    </div>

                </form>
            </div>

        </div>
    </body>
</html>
