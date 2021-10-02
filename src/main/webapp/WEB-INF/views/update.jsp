<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<div class="container">
    <form action="<c:url value='/save?id=${post.id}'/>" method='POST'>
        <table>
            <tr>
                <td>Название:<input type='text' name='name' value="${post.name}"></td>
            </tr>
            <tr>
                <td>Описание:<input type='text' name='text' value="${post.desc}"></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>

