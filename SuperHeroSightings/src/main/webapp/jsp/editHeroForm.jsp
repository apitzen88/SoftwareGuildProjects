<%-- 
    Document   : editHeroForm
    Created on : Nov 2, 2017, 1:52:05 PM
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
        <title>Edit Location</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit SuperHero</h1>
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
            <div class="col-md-offset-1" style="width: 400px">  
                <sf:form class="form-horizontal" role="form" modelAttribute="hero"
                         action="updateHero" method="POST">

                    <div class="form-group">

                        <label for="add-hero-name" 
                               class="col-md-4 control-label">Hero Name:</label>

                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-hero-name" path="heroName" 
                                      placeholder="Hero Name"/>
                            <sf:errors path="heroName" cssclass="error"></sf:errors>
                            </div>

                        </div>

                        <div class="form-group">

                            <label for="add-real-name" 
                                   class="col-md-4 control-label">Real Name:</label>

                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-real-name" path="realName" 
                                      placeholder="Real Name"/>
                            <sf:errors path="realName" cssclass="error"></sf:errors>
                            </div>

                        </div>
                        <div class="form-group">

                            <label for="add-hero-description" 
                                   class="col-md-4 control-label">Description:</label>

                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-hero-description" path="description" 
                                      placeholder="Description"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            </div>

                        <sf:hidden path="heroId"/>
                    </div>
                    
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Hero"/>
                    </div>

                </sf:form>
            </div>
        </div>
    </body>
</html>
