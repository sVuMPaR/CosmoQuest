<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Question</title>
</head>
<body>
    <h1>Question</h1>
    <p style="color: red;"><%= request.getAttribute("message") %></p>
    <form method="POST" action="${pageContext.request.contextPath}/game">
        <label>Do you accept?</label>
        <select name="answer" required>
            <option value="">-- Select --</option>
            <option value="accept">Accept</option>
            <option value="decline">Decline</option>
        </select>
        <button type="submit">Submit</button>
    </form>
</body>
</html>