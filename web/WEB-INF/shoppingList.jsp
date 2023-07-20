<%-- 
    Document   : shoppingList
    Created on : Jul 6, 2023, 3:12:02 PM
    Author     : Fortune Agbone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    
    <body>
        <h2>Shopping List</h2>
        
        <span>Hello, ${sessionScope.username}!</span>
        
         <a href="ShoppingList?action=logout">Logout</a><br/><br/>
         
        <form action="ShoppingList" method="post">
            <input type="hidden" name="action" value="add">
            Add item: <input type="text" name="item" required>
            <input type="submit" value="Add">
        </form>
       
         <form action="ShoppingList" method="post">
             <input type="hidden" name="action" value="delete">
             <ul>
                 <c:forEach items="${sessionScope.shoppingList}" var="item">
                     <li>
                         <input type="radio" name="itemToDelete" value="${item}">
                         ${item}
                     </li>
                 </c:forEach>
             </ul>
             <input type="submit" value="Delete">
         </form>
    </body>
</html>
