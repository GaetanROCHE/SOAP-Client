<jsp:include page="header.jsp" />
<script>
    $("selector").data('typeahead').source = ${nomPays};


</script>
<head>
    <div class="container">
        <div class="row">
            <div class="well">
                <form>
                    <fieldset>
                        <div class="form-group">
                            <label for="query">Search:</label>
                            <input type="text" class="form-control" name="query" id="query" placeholder="Start typing something to search...">
                        </div>
                        <button type="submit" class="btn btn-primary">Search</button>
                    </fieldset>
                </form>
            </div>
        </div>
    </div>
</head>
<body>

</body>
</html>
