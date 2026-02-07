<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Это финал боец</title>
</head>
<body>
<h1>Это финал боец боец</h1>
<p>Ты отлично справился, боец!</p>

<%
    Integer victories = (Integer) session.getAttribute("victories");
%>
<h2>Квест №3</h2>
<p>Побед: <%= victories %></p>
<p>Твоя отвага и упорство, навсегда останется в памяти космодесанта, а теперь в</p>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red;"><%= request.getAttribute("message") %></p>
<% } %>

<button onclick="location.href='/result.jsp'">в казарму!</button>
<a href="/reset">Начать заново</a>
</body>
</html>