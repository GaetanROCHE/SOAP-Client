<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp" />
<body>
 <h1>${pays.nomPays}</h1>
 <div> <h3> Capitale : </h3> ${pays.nomCapitale}</div>
 <div> <h3> nombre d'habitants : </h3> ${pays.nbhabitants}</div>
 <h3> Carte : </h3>
 <div><img src="https://maps.googleapis.com/maps/api/staticmap?center=${pays.nomPays}&size=100x100" /></div>
</body>
</html>
