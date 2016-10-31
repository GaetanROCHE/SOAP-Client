<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="header.jsp" />



<body>
<div class="row">
    <div class="col-md-3">
        <c:forEach items="${list_pays}" var="pays">
            <div><a href="Servlet?action=detailsPays&pays=${pays.nomPays}">${pays.nomPays}</div>
        </c:forEach>


    </div>
    <div class="col-md-9">.col-md-6</div>
</div>
</body>