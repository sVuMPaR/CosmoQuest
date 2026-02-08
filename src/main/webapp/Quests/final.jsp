<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Это финал боец</title>
</head>
<body>
<h1>Это финал, боец!</h1>
<p>Ты отлично справился, боец!</p>

<%
    Integer victories = (Integer) session.getAttribute("victories");
    if (victories == null) victories = 0;
%>
<h2>Финал</h2>
<p>Побед: <%= victories %></p>
<p>Твоя отвага и упорство навсегда останутся в памяти космодесанта. А теперь — в казарму!</p>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red;"><%= request.getAttribute("message") %></p>
<% } %>

<button onclick="location.href='${pageContext.request.contextPath}/result.jsp'">В казарму!</button>
<a href="${pageContext.request.contextPath}/reset">Начать заново</a>
</body>
</html>
