<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div class="container">
    <form name='post' action="<c:url value='/save'/>" method='POST'>
        <p>Создадим пост</p>
        <table>
            <tr>
                <td>Name:</td>
                <td><input type='text' name='name'></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type='desc' name='desc'/></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="submit"/></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>