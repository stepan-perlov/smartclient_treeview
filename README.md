# Smartclient treeView

Example of usage https://www.smartclient.com/product/smartclient.jsp.

## Requirement
 - Ubuntu 14.04
 - Java 8
 - Postgresql 9.6+
 - IntelliJ IDEA

## Installation
```bash
$ cd <PROJECT_DIR>
$ ./deps.sh
$ inv env.init db.create db.init
```

## Reset database
```bash
$ inv db.create --reset db.init
```

## Database configuration
app/res/probe/common/config.json

## Running application
In idea run "Tomcat 8.5.20"
