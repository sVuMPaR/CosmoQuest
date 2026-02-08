<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Третья задача боец</title>
</head>
<body>
<h1>Третья задача боец</h1>
<p>Ты отлично справился, боец!</p>

<%
    Integer victories = (Integer) session.getAttribute("victories");
    if (victories == null) victories = 0;
%>
<h2>Квест №3</h2>
<p>Побед: <%= victories %></p>
<p>Ты добрался до вершины, хоть и не без препятствий, нужно</p>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red;"><%= request.getAttribute("message") %></p>
<% } %>

<form action="${pageContext.request.contextPath}/game" method="post">
    <input type="radio" name="answer" value="accept">вызвать подкрепление<br>
    <input type="radio" name="answer" value="decline">отступить? ну ты серьёзно, столько усилий, чтобы отступить?<br>
    <input type="submit" value="Ответить">
</form>
<a href="${pageContext.request.contextPath}/reset">Начать заново</a>
</body>
</html>
