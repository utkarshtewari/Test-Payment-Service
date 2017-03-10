rm -fr build
mkdir build
cp ../target/accounting-query-service-0.0.1-SNAPSHOT.jar build
docker build -t accounting-query-service .