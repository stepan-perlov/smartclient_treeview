var dataSourceMap = {};
dataSourceList.forEach(function(ds) {
    isc.DataSource.create(ds);
    dataSourceMap[ds.ID] = ds;
    console.info("Create DataSource `" + ds.ID + "`")
});

var treeGridSettingsDS = isc.DataSource.getDataSource("tree_grid_settings");

var uiSettingsMap = {};
uiSettingsList.forEach(function(uiSettings) {
    uiSettingsMap[uiSettings.id] = uiSettings;
})

var locationsTreeGridSettings = uiSettingsMap.locations_tree;

var locationsDataFields = dataSourceMap.locations_data.fields.map(function(field) {
    var newField = {};
    for (var key in field) {
        newField[key] = field[key]
    }
    newField.name = "data." + newField.name;
    return newField;
})

var treeGridSettingsForm = isc.DynamicForm.create({
    ID: "tree_settings_form",
    width: 640,
    titleWidth: 50,
    dataSource: "tree_grid_settings",
    isGroup: true,
    groupTitle: "Settings",
    fields: [
        {"name": "width"},
        {"name": "height"},
        {"name": "submit", "title": "Submit", "type": "button", "click": function(form) {
            treeGridSettingsDS.updateData(form.getValues(), function (dsResponse, data, dsRequest) {
                locationsTreeGrid.setWidth(data.width);
                locationsTreeGrid.setHeight(data.height);
            });
        }}
    ],
    values: locationsTreeGridSettings
});

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
    height: locationsTreeGridSettings.height,
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
        treeGridSettingsForm,
        locationsTreeGrid
    ]
});
