<%-- 
    Document   : editPowerForm
    Created on : Nov 1, 2017, 11:23:26 AM
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
        <title>Edit SuperPower</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit SuperPower</h1>
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
                    <sf:form class="form-horizontal" role="form" modelAttribute="power"
                             action="updatePower" method="POST">

                        <div class="form-group">

                            <label for="description" 
                                   class="col-md-4 control-label">Power:</label>

                            <div class="col-md-8">
                                <sf:input type="text" class="form-control" 
                                          id="add-power-desc" path="description" 
                                          placeholder="Description"/>
                                <sf:errors path="description" cssclass="error"></sf:errors>

                                <sf:hidden path="superPowerId"/>
                            </div>

                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Update Power"/>
                            </div>

                        </sf:form>
                    </div>
                </div>
            </div>

        </div>
    </body>
</html>
