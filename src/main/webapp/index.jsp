<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Космический квест</title>
</head>
<body>
<h1>Добро пожаловать на борт!</h1>
<p>Вы стоите в космическом порту. Перед вами стоит задача уничтожить полчище ксеноморфов,вы не вправе отказываться!<br>Вы! Не вправе отступить!</p>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red;"><%= request.getAttribute("message") %></p>
<% } %>

<button onclick="location.href='/Quests/quest0.jsp'">В БОЙ!</button>
</body>
</html>
