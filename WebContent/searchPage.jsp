<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="header.jsp" />

<head>
    <div class="container">
        <div class="row">
            <div class="well">
                <div class="form-group">
                    <label for="pays">Search:</label>
                    <input type=text list=pays >
                    <datalist id=pays >
                        <c:forEach items="${list_pays}" var="pays">
                            <option> ${pays.nomPays}
                        </c:forEach>
                    </datalist>
                </div>
            </div>
        </div>
    </div>
</head>
<body>

</body>
</html>
