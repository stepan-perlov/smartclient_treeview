/*
DataSource example

isc.DataSource.create({
    ID: "locations",
    dataFormat: "json",
    dataURL: "/api/locations",
    fields:[
        {title:"Id", name:"id", primaryKey: true},
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
*/

dataSourceList.forEach(function(ds) {
    isc.DataSource.create(ds);
    console.info("Create DataSource `" + ds.ID + "`")
})

var locationsTreeGrid = isc.TreeGrid.create({
    ID: "locations_tree",
    initialData: locationsList,
    styleName: "listGrid locationsTree",
    dataSource: "locations",
    autoFetchData: true,
    fields: [
        {name: "name"},
        {name: "type"},
        {name: "icon"},
        {name: "data"}
    ],
    width: "100%",
    height: "100%"
});

isc.VLayout.create({
    ID: "main",
    width: "100%",
    height: "100%",
    members: [
        locationsTreeGrid
    ]
})
