<%-- 
    Document   : editOrgForm
    Created on : Oct 31, 2017, 1:56:41 PM
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

        <title>Edit Organization</title>
    </head>
    <body>
        <div class="container">
            <h1>Edit Organization</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"><a href="${pageContext.request.contextPath}/index.jsp">Home</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySightingsPage">Sightings</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayLocationsPage">Locations</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displaySuperHeroesPage">SuperHeroes</a></li>
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                </ul>    
            </div>


            <div class="col-md-offset-3" style="width: 400px">  
                <sf:form class="form-horizontal" role="form" modelAttribute="org"
                         action="updateOrg" method="POST">

                    <div class="form-group">

                        <label for="orgName" 
                               class="col-md-4 control-label">Organization Name:</label>

                        <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-org-name" path="orgName" 
                                      placeholder="Organization Name"/>
                            <sf:errors path="orgName" cssclass="error"></sf:errors>
                            </div>

                        </div>
                            
                        <div class="form-group">
                            <label for="description" 
                                   class="col-md-4 control-label">Description:</label>

                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-org-description" path="description" 
                                      placeholder="Description"/>
                            <sf:errors path="description" cssclass="error"></sf:errors>
                            </div>

                        </div>
                            
                        <div class="form-group">

                            <label for="contact" 
                                   class="col-md-4 control-label">Contact:</label>

                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-org-contact" path="contact" 
                                      placeholder="Contact"/>
                            <sf:errors path="contact" cssclass="error"></sf:errors>
                            </div>

                        </div>

                        <div class="form-group">

                            <label for="baseLocation" 
                                   class="col-md-4 control-label">Base Location:</label>

                            <div class="col-md-8">
                            <sf:input type="text" class="form-control" 
                                      id="add-org-baseLocation" path="baseLocation" 
                                      placeholder="Base Location"/>
                            <sf:errors path="baseLocation" cssclass="error"></sf:errors>
                            
                            <sf:hidden path="orgId"/>
                            </div>

                        </div>

                        <div class="col-md-offset-4 col-md-8">
                            <input type="submit" class="btn btn-default" value="Update Organization"/>
                        </div>

                </sf:form>
            </div>  

        </div>
</html>
