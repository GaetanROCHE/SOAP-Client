<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
 <h1>${pays.nomPays}</h1>

 <div>${pays.nomPays}</div>
 <div>${pays.nomCapitale}</div>
 <div>${pays.nbhabitants}</div>
 <div><img src="https://maps.googleapis.com/maps/api/staticmap?center=${pays.nomPays}&size=100x100" /></div>
</body>
</html>
