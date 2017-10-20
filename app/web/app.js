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

var dataSourceMap = {};
dataSourceList.forEach(function(ds) {
    isc.DataSource.create(ds);
    dataSourceMap[ds.ID] = ds;
    console.info("Create DataSource `" + ds.ID + "`")
});

var locationsDataFields = dataSourceMap.locations_data.fields.map(function(field) {
    var newField = {};
    for (var key in field) {
        newField[key] = field[key]
    }
    newField.name = "data." + newField.name;
    return newField;
})

var locationsTreeGrid = isc.TreeGrid.create({
    ID: "locations_tree",
    initialData: locationsList,
    dataSource: "locations",
    autoFetchData: true,
    canEdit: true,
    canRemoveRecords: true,
    fields: [
        {name: "name"},
        {name: "type"}
    ].concat(
        locationsDataFields, [
            {name: "icon", type: "image", hidden: true}
        ]
    ),
    width: "100%",
    height: "100%"
});

main = isc.VLayout.create({
    ID: "main",
    width: "100%",
    height: "100%",
    members: [
        locationsTreeGrid
    ]
})

