<%-- 
    Document   : editLocationForm
    Created on : Oct 26, 2017, 4:48:00 PM
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
            <h1>Edit Location</h1>
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
            <div class="col-md-offset-1" style="width: 400px">  
                <sf:form class="form-horizontal" role="form" modelAttribute="location"
                         action="updateLocation" method="POST">
                    
                    <div class="form-group">
                        
                        <label for="add-location-name" 
                               class="col-md-4 control-label">Location Name:</label>
                               
                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-location-name" path="locationName" 
                                      placeholder="Location Name"/>
                            <sf:errors path="locationName" cssclass="error"></sf:errors>
                            </div>
                            
                        </div>
                            
                        <div class="form-group">
                            
                            <label for="add-location-description" 
                                   class="col-md-4 control-label">Description:</label>
                                   
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-location-description" path="description" 
                                      placeholder="Description"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            </div>
                            
                        </div>
                            
                        <div class="form-group">
                            
                            <label for="add-location-address" 
                                   class="col-md-4 control-label">Address:</label>
                                   
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-location-address" path="address" 
                                      placeholder="Address"/>
                            <sf:errors path="address" cssclass="error"></sf:errors>
                            </div>
                            
                        </div>
                            
                        <div class="form-group">
                            <label for="add-location-city" 
                                   class="col-md-4 control-label">City:</label>
                                   
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-location-city" path="city" 
                                      placeholder="City"/>
                            <sf:errors path="city" cssclass="error"></sf:errors>
                            </div>
                            
                        </div>
                            
                        <div class="form-group">
                            
                            <label for="add-location-latitude" 
                                   class="col-md-4 control-label">Latitude:</label>
                                   
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-location-latitude" path="latitude" 
                                      placeholder="Latitude"/>
                            <sf:errors path="latitude" cssclass="error"></sf:errors>
                            </div>
                            
                        </div>
                            
                        <div class="form-group">
                            
                            <label for="add-location-longitude" 
                                   class="col-md-4 control-label">Longitude:</label>
                                   
                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-location-longitude" path="longitude" 
                                      placeholder="Longitude"/>
                            <sf:errors path="longitude" cssclass="error"></sf:errors>
                            
                            <sf:hidden path="locationId"/>
                            </div>
                            
                        </div>
                            
                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Update Location"/>
                        </div>
                            
                </sf:form>
            </div>  
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>
