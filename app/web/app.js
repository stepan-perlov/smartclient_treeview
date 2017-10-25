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

var uiSettingsMap = {};
uiSettingsList.forEach(function(uiSettings) {
    uiSettingsMap[uiSettings.id] = uiSettings;
})

var locationsTreeGridSettings = uiSettingsMap.locations_tree.settings;

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
        //{name: "type"}
    ].concat(
        locationsDataFields, [
            {name: "icon", type: "image", hidden: true},
            {name: "actions",
                width: "24px",
                showTitle: false,
                canSort: false,
                canEdit: false,
                showDefaultContextMenu: false}
        ]
    ),
    width: locationsTreeGridSettings.width,
    showRecordComponents: true,
    showRecordComponentsByCell: true,
    recordComponentPoolingMode: "viewport",
    createRecordComponent: function(record, columnNumber) {
       var fieldName = this.getFieldName(columnNumber);
       if (fieldName == "actions" && record.type != "COUNTRY") {
           var acionsLayout = isc.HLayout.create({
               width: "24px",
               height: "24px",
               align: "center"
           });

           var newItemButton = isc.ImgButton.create({
               src: "/icons/actions/create.png",
               layoutAlign: "center",
               width: "16px;",
               height: "16px",
               grid: this,
               click: function(record) {
                  return function() {
                      locationsTreeGrid.startEditingNew({parent: record.id});
                  }
               }(record),
               showDown: false,
               showRollOver: false
           })

           acionsLayout.addMember(newItemButton)
           return acionsLayout;
       }
    }
});

main = isc.VLayout.create({
    ID: "main",
    width: "100%",
    height: "100%",
    members: [
        locationsTreeGrid
    ]
});
