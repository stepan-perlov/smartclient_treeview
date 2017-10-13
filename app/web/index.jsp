<%@ taglib uri="isomorphic" prefix="isomorphic" %>
<html>
<head>
    <isomorphic:loadISC skin="Tahoe"/>
</head>
<body>
<script>
    isc.DataSource.create({
        ID: "locations",
        dataFormat: "json",
        dataURL: "/api/locations",
        fields:[
            {title:"Type", name:"type"},
            {title:"Name", name:"name"},
            {title:"Icon", name:"icon"},
            {title:"Data", name:"data"}
        ]
    });
    isc.DataSource.getDataSource("locations").fetchData({"parent": null}, function(dsResponse, data) {
        console.log(data);
    });
    isc.DataSource.getDataSource("locations").fetchData({"parent": -5}, function(dsResponse, data) {
        console.log(data);
    });
    isc.DataSource.getDataSource("locations").fetchData({"parent": -4}, function(dsResponse, data) {
        console.log(data);
    });
    isc.DataSource.getDataSource("locations").fetchData({"parent": -1}, function(dsResponse, data) {
        console.log(data);
    });
    isc.DataSource.getDataSource("locations").fetchData({"parent": 10000}, function(dsResponse, data) {
        console.log(data);
    });
</script>
</body>
</html>
