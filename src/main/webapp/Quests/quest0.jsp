<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Первая задача боец</title>
</head>
<body>
<h2>Квест №1</h2>
    <!-- Получаем victories из сессии -->
    <%
        Integer victories = (Integer) session.getAttribute("victories");
        if (victories == null) {
            victories = 0; // если в сессии нет — считаем 0
        }
    %>
<p>Побед: <%= victories %></p>
<h1>Первая задача боец</h1>
<p>Вы десантируетесь с корабля на поле в окружении чёрных и мерзких ксеносов, у вас всего один вариант,</p>

<% if (request.getAttribute("message") != null) { %>
<p style="color: red;"><%= request.getAttribute("message") %></p>
<% } %>

<form action="${pageContext.request.contextPath}/game" method="post">
    <input type="radio" name="answer" value="accept">Пошибать всем бошки!<br>
    <input type="radio" name="answer" value="decline">Отступить?<br>
    <input type="submit" value="Ответить">
</form>
<a href="${pageContext.request.contextPath}/reset">Начать заново</a>
</body>
</html>
