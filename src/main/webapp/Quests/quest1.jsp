<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вторая задача боец</title>
</head>
<body>
<h1>Вторая задача боец</h1>
<p>Ты отлично справился, боец!</p>

<%
    Integer victories = (Integer) session.getAttribute("victories");
%>
<h2>Квест №2</h2>
<p>Побед: <%= victories %></p>
<p>Осталось немного и ты сможешь вернуться в казарму.<br>Следующая миссия состоит в захвате высотной точки, для лучшего обзора и возможности вызвать подкрепление</p>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red;"><%= request.getAttribute("message") %></p>
<% } %>

<form action="/game" method="post">
    <input type="radio" name="answer" value="accept">Ринуться в гору для занятия позиции<br>
    <input type="radio" name="answer" value="decline">Отступить?<br>
    <input type="submit" value="Ответить">
</form>
<a href="/reset">Начать заново</a>
</body>
</html>