<%@ taglib uri="isomorphic" prefix="isomorphic" %>
<html>
<head>
    <isomorphic:loadISC isomorphicURI="/isomorphic" skin="Tahoe"/>
    <link href="/app.css" rel="stylesheet" />
</head>
<body>
    <div id="main">
    </div>
    <script>
        var dataSourceList = JSON.parse('<%= (String)request.getAttribute("dataSourceList") %>')
        var locationsList = JSON.parse('<%= (String)request.getAttribute("locationsList") %>');
    </script>
    <script src="/app.js"></script>
</body>
</html>
