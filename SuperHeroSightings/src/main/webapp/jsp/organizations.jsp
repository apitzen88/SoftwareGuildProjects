<%-- 
    Document   : organizations
    Created on : Oct 25, 2017, 2:10:01 PM
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
        <title>Organizations</title>
    </head>
    <body>
        <div class="container">
            <h1>SuperHero Sightings</h1>
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

            <div class="row">
                <div class="col-md-6">
                    <h2>Organizations</h2>
                    <table id="locationsTable" class="table table-hover">
                        <tr>
                            <th width="20%">Organization</th>
                            <th width="40%">Description</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="10%>"></th>
                        </tr>
                        <c:forEach var="currentOrg" items="${orgs}">

                            <tr>

                                <td width="20%">
                                    <c:out value="${currentOrg.orgName}"/>
                                </td>
                                <td width="40%">
                                    <c:out value="${currentOrg.description}"/>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-default">Info</button>
                                        <div class="dropdown-content">
                                            <p>
                                                Base Location: <br/>
                                                <c:out value="${currentOrg.baseLocation}"/>
                                            </p>
                                            <p>
                                                Contact: <br/>
                                                <c:out value="${currentOrg.contact}"/>
                                            </p>
                                            <p>
                                                Heroes: <br/>
                                                <c:forEach var="hero" items="${currentOrg.heroes}">
                                                    <c:out value="${hero.heroName}"></c:out><br/>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </td>
                                <td width="10%">
                                    <a href="displayAddHeroesOrg?orgId=${currentOrg.orgId}">
                                        <button class="btn btn-default">Add/Remove Heroes</button>
                                    </a>
                                </td>
                                <td width="10%">
                                    <a href="displayEditOrgForm?orgId=${currentOrg.orgId}">
                                        <button class="btn btn-default">Edit</button>
                                    </a>  
                                </td>
                                <td width="10%">
                                    <a href="deleteOrg?orgId=${currentOrg.orgId}">
                                        <button class="btn btn-default">Delete</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="col-md-1"></div>
                
                <div class="col-md-5">
                    <h2>Add Organization</h2>
                    <hr/>

                    <form class="form-horizontal"
                          id="addOrg"
                          role="form"
                          method="POST"
                          action="createOrg">

                        <div class="form-group">
                            <label for="orgName"
                                   class="col-md-4 control-label">Organization Name:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="orgName" placeholder="Organization Name" required/>
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
                            <label for="contact"
                                   class="col-md-4 control-label">Contact:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="contact" placeholder="Contact" required/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="baseLocation"
                                   class="col-md-4 control-label">Base Location:
                            </label>
                            <div class="col-md-8">
                                <input type="text" 
                                       maxlength="45"
                                       class="form-control" 
                                       name="baseLocation" placeholder="Base Location" required/>
                            </div>
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
                                   value="Add Organization"/>
                        </div>
                    </div>

                    </form>
                    
                </div>
            </div>
        </div>

    </div>





</div>
<script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>       
</body>
</html>