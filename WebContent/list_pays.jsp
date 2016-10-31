<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />
<%--
  Created by IntelliJ IDEA.
  User: thomasd
  Date: 31/10/16
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>

<body>
<table class="table">
    <tr>
        <th>Pays</th>
        <th>Capitale</th>
        <th>Nombre d'habitants</th>
        <th>Maps</th>
    </tr>
    <c:forEach items="${list_pays}" var="pays">
        <tr>
            <td>${pays.nomPays}</td>
            <td>${pays.nomCapitale}</td>
            <td>${pays.nbhabitants}</td>
            <td><img src="https://maps.googleapis.com/maps/api/staticmap?center=${pays.nomPays}&size=100x100" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
