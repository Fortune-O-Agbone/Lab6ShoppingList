<%-- 
    Document   : register
    Created on : Jul 6, 2023, 3:11:51 PM
    Author     : Fortune Agbone
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    
    <body>
        <h1>Shopping List</h1>
        
        <form action="ShoppingList" method="post">
            <input type="hidden" name="action" value="register">
            Username: <input type="text" name="username">
            <input type="submit" value="Register name">
        </form>
        <p>${message}</p>
    </body>
</html>
