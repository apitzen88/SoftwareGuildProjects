<%-- 
    Document   : editSuperPowers
    Created on : Oct 31, 2017, 4:48:58 PM
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
        <title>Edit/Delete Powers</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit/Delete Powers</h1>
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

            <div class="col-md-offset-3">

                <div class="col-md-5">
                    <table id="powerTable" class="table table-hover">
                        <th>Power</th>
                        <th></th>
                        <th></th>
                            <c:forEach var="currentPower" items="${powers}">
                            <tr>
                                <td>  
                                    <c:out value="${currentPower.description}"/>
                                </td>
                                <td>
                                    <a href="displayEditPowerForm?powerId=${currentPower.superPowerId}">
                                        <button class="btn btn-default">Edit</button>
                                    </a>
                                </td>
                                <td>
                                    <a href="deletePower?powerId=${currentPower.superPowerId}">
                                        <button class="btn btn-default">Delete</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
                <a href="displaySuperHeroesPage">
                    <button class="btn btn-default">Go Back</button>
                </a>
            </div>

        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
