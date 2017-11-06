<%-- 
    Document   : recentSightings
    Created on : Nov 5, 2017, 9:55:55 PM
    Author     : apitz_000
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <title>Recent Sightings</title>
    </head>
    <body>
        <h2>Most Recent Sightings</h2>

        
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
                </tr>
            </c:forEach>
        </table>
                            
         <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>                   
    </body>
</html>
