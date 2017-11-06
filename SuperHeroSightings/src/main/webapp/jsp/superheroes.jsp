<%-- 
    Document   : superheroes
    Created on : Oct 25, 2017, 2:10:36 PM
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
            table{
                max-width: 100%;
            }
        </style>
        <title>SuperHeroes</title>
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
                    <li role="presentation" class="active"><a href="${pageContext.request.contextPath}/displaySuperHeroesPage">SuperHeroes</a></li>
                    <li role="presentation"><a href="${pageContext.request.contextPath}/displayOrganizationsPage">Organizations</a></li>
                </ul>    
            </div>

            <div class="row">
                <div class="col-md-6">
                    <h2>SuperHeroes</h2>
                    <table id="locationsTable" class="table table-hover">
                        <tr>
                            <th width="20%">Hero</th>
                            <th width="40%">Description</th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="10%"></th>
                            <th width="10%>"></th>
                        </tr>
                        <c:forEach var="currentHero" items="${heroes}">

                            <tr>

                                <td width="20%">
                                    <c:out value="${currentHero.heroName}"/>
                                </td>
                                <td width="40%">
                                    <c:out value="${currentHero.description}"/>
                                </td>
                                <td>
                                    <div class="dropdown">
                                        <button class="btn btn-default">Info</button>
                                        <div class="dropdown-content">
                                            <p>
                                                Real Name: <br/><c:out value="${currentHero.realName}"/>
                                            </p>
                                            <p>
                                                Powers: <br/>
                                                <c:forEach var="power" items="${currentHero.superPowers}">
                                                    <c:out value="${power.description}"></c:out><br/>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </td>

                                <td width="10%">
                                    <a href="displayAddPowersHero?heroId=${currentHero.heroId}">
                                        <button class="btn btn-default">Add/Remove Powers</button>
                                    </a>
                                </td>
                                <td width="10%">
                                    <a href="displayEditHeroForm?heroId=${currentHero.heroId}">
                                        <button class="btn btn-default">Edit</button>
                                    </a>  
                                    </div>
                                </td>
                                <td width="10%">
                                    <a href="deleteHero?heroId=${currentHero.heroId}">
                                        <button class="btn btn-default">Delete</button>
                                    </a>
                                </td>
                                
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <div class="col-md-1"></div>

                <div class="col-md-5">
                    <h2>Add Hero</h2>
                    <hr/>

                    <div>
                        <form class="form-horizontal"
                              id="addOrg"
                              role="form"
                              method="POST"
                              action="createHero">

                            <div class="form-group">
                                <label for="heroName"
                                       class="col-md-4 control-label">Hero Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           maxlength="25"
                                           class="form-control" 
                                           name="heroName" placeholder="Hero Name" required/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="realName"
                                       class="col-md-4 control-label">Real Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" 
                                           maxlength="45"
                                           class="form-control" 
                                           name="realName" placeholder="Real Name" required/>
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
                            <div class="panel-group">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" href="#collapse1">Add Powers</a>
                                        </h4>
                                    </div>
                                    <div id="collapse1" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <c:forEach var="currentPower" items="${powers}">
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

                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" 
                                           class="btn btn-default" 
                                           value="Add Hero"/>
                                </div>
                            </div>

                        </form>
                    </div>


                    <div class="row">
                        <h2>Add Power</h2>
                        <hr/>
                        <div>
                            <form class="form-horizontal"
                                  id="addOrg"
                                  role="form"
                                  method="POST"
                                  action="createPower">

                                <div class="form-group">
                                    <label for="description"
                                           class="col-md-4 control-label">Power:
                                    </label>
                                    <div class="col-md-8">
                                        <input type="text" 
                                               maxlength="45"
                                               class="form-control" 
                                               name="description" placeholder="Description" required/>
                                    </div>
                                </div>


                                <div class="form-group">
                                    <div class="col-md-offset-4 col-md-8">
                                        <input type="submit" 
                                               class="btn btn-default" 
                                               value="Add Power"/>
                                    </div>
                                </div>

                            </form>

                            <div  class="col-md-offset-4 col-md-8">
                                <a href="displayEditSuperPowers">
                                    <button class="btn btn-default" value="">Edit/Delete Powers</button>
                                </a>
                            </div>

                        </div>
                    </div>


                </div>
            </div>
            <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
            <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>       
    </body>
</html>
