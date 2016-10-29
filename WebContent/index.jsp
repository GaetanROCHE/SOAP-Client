<%@ page import="requeteSOAP.EnvoiMessageSOAP"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <meta name="revisit-after" content="15 days" />
    <meta name="language" content="fr" />
    <meta name="robots" content="all" />
    <title>Gestion des Pays</title>
    <link rel="stylesheet" href="css/mesStyles.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<% pageContext.setAttribute("list_pays", new EnvoiMessageSOAP().emissionReceptionUnPays("http://service")); %>
<table class="table">
    <tr>
        <th>Pays</th>
        <th>Capitale</th>
        <th>Nombre d'habitants</th>
        <th>Maps</th>
    </tr>
    <c:forEach var="pays" items="${list_pays}">
        <tr>
            <td>${pays.nomPays}</td>
            <td>${pays.nomCapitale}</td>
            <td>${pays.nbhabitants}</td>
            <td><img src="https://maps.googleapis.com/maps/api/staticmap?center=${pays.nomPays}&size=100x100" /></td>
        </tr>
    </c:forEach>
</table>
</body>