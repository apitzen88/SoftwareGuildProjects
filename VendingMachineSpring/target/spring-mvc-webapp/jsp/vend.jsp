<%-- 
    Document   : vend
    Created on : Oct 8, 2017, 9:11:59 PM
    Author     : apitz_000
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Vending Machine</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container">

            <h1 align="center">Vending Machine</h1>
            <hr>


            <div class="row">


                <div class="col-xs-7 well" id="item-wrapper">
                    <c:forEach var="currentItem" items="${items}">
                        <div class="col-xs-4" style="padding:15px">
                            <form method="POST" action="selectItem">
                                <button type="submit" 
                                        name="itemButton" 
                                        value="${currentItem.id}" 
                                        class="btn btn-default btn-block" 
                                        id="item-label ${currentItem.id}">
                                    <p>
                                        <c:out value="${currentItem.id}"/>
                                    </p>
                                    <p>
                                        <b><c:out value="${currentItem.name}"/></b>
                                    </p>
                                    <p>
                                        $ <c:out value="${currentItem.price}"/>
                                    </p>
                                    <p>
                                        In Stock: <c:out value="${currentItem.inventory}"/>
                                    </p>
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </div>


                <div class="col-xs-1"></div>


                <div class="col-xs-4" id="menu-wrapper">

                    <div id="add-money-row" class="row">
                        
                        <div id="balance-display" class="well">
                            <h3 id="menu-header">Balance</h3>
                            <div id="screen">
                                <h3 id="balance">$ ${currentBalance}</h3>
                            </div>


                            <div id="add-money-btns">
                                <table id="button-table" align="center" style="table-layout:fixed;width:100%">
                                    <tr>
                                    <form method="POST" action="addDollar">
                                        <td class="money-button" align="center">
                                            <button type="submit" 
                                                    class="btn btn-default btn-block" 
                                                    id="add-dollar">Dollar
                                            </button>
                                        </td>
                                    </form>
                                    <form method="POST" action="addQuarter">
                                        <td class="money-button" align="center">
                                            <button type="submit" 
                                                    class="btn btn-default btn-block" 
                                                    id="add-quarter">Quarter
                                            </button>
                                        </td>
                                    </form>
                                    </tr>
                                    <tr>
                                    <form method="POST" action="addDime">
                                        <td class="money-button" align="center">
                                            <button type="submit" 
                                                    class="btn btn-default btn-block" 
                                                    id="add-dime">Dime
                                            </button>
                                        </td>
                                    </form>
                                    <form method="POST" action="addNickel">
                                        <td class="money-button" align="center">
                                            <button type="submit" 
                                                    class="btn btn-default btn-block" 
                                                    id="add-nickel">Nickel
                                            </button>
                                        </td>
                                    </form>
                                    </tr>
                                </table>

                            </div>
                        </div>
                        <hr>
                    </div>


                    <div id="purchase-row" class="row" vertical-align="middle">
                        <div id="message-display" class="well">
                            <h3 id="menu-header">Messages</h3>
                            <div id="screen">
                                <h3 id="message">${currentMessage}</h3>
                            </div>
                            <form method="POST" action="purchaseItem">
                                <table id="item-table" align="center">
                                    <tr id="item-label">
                                        <td>
                                            <h4>Item Id: </h4>
                                        </td>
                                        <td>
                                            <input name="itemId" 
                                                   value="${selectedItem}" 
                                                   type="text" class="form-control" 
                                                   id="item-screen" placeholder="Item Id" required/>
                                        </td>
                                    </tr>
                                </table>
                                <button type="submit" 
                                        class="btn btn-default btn-block" 
                                        id="purchase-button">Make Purchase
                                </button>
                            </form>
                        </div>
                        <hr>
                    </div>
                    </form>
                    <div id="change-row" class="row" vertical-align="middle">
                        <div id="change-display" class="well">
                            <h3 id="menu-header">Change</h3>
                            <div id="screen">
                                <h3 id="change"></h3>
                            </div>

                            <table id="change-table" align="center" style="table-layout:fixed;width:100%">
                                <tr>
                                    <td>Quarters: <input type="text" 
                                                         class="form-control" 
                                                         id="quarters-screen" 
                                                         value="${currentChange.quarters}"
                                                         style="width:95%"
                                                         placeholder="0" required/>
                                    </td>

                                    <td>Dimes: <input type="text" 
                                                      class="form-control" 
                                                      id="dimes-screen" 
                                                      value="${currentChange.dimes}"
                                                      style="width:95%"
                                                      placeholder="0" required/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>Nickels: <input type="text" 
                                                        class="form-control" 
                                                        id="nickels-screen" 
                                                        value="${currentChange.nickels}"
                                                        style="width:95%"
                                                        placeholder="0" required/>
                                    </td>

                                    <td>Pennies: <input type="text" 
                                                        class="form-control" 
                                                        id="pennies-screen" 
                                                        value="${currentChange.pennies}"
                                                        style="width:95%"
                                                        placeholder="0" required/>
                                    </td>
                                </tr>
                            </table>

                            <form method ="POST" action="makeChange">
                                <button type="submit" 
                                        class="btn btn-default btn-block" 
                                        id="change-button">Return Change
                                </button>
                            </form>
                        </div>
                    </div>

                </div>

            </div>
            <hr>
        </div>

        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>


</html>
