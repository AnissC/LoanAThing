PROJECT_ROOT_DIR=$(shell pwd)
DB_LEGACY_ENGINE=mysql
DB_LEGACY_HOST=localhost
DB_LEGACY_PORT=3306
DB_LEGACY_NAME=lat
DB_LEGACY_USER=root
DB_LEGACY_PASS=root

all: database

database:
	cp src/com/lat/dao/dao.properties.dist src/com/lat/dao/dao.properties
	sed -i '' 's@{DB_LEGACY_ENGINE}@${DB_LEGACY_ENGINE}@g' src/com/lat/dao/dao.properties
	sed -i '' 's@{DB_LEGACY_HOST}@${DB_LEGACY_HOST}@g' src/com/lat/dao/dao.properties
	sed -i '' 's@{DB_LEGACY_PORT}@${DB_LEGACY_PORT}@g' src/com/lat/dao/dao.properties
	sed -i '' 's@{DB_LEGACY_NAME}@${DB_LEGACY_NAME}@g' src/com/lat/dao/dao.properties
	sed -i '' 's@{DB_LEGACY_USER}@${DB_LEGACY_USER}@g' src/com/lat/dao/dao.properties
	sed -i '' 's@{DB_LEGACY_PASS}@${DB_LEGACY_PASS}@g' src/com/lat/dao/dao.properties
