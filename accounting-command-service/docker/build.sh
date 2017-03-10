rm -fr build
mkdir build
cp ../target/accounting-command-service-0.0.1-SNAPSHOT.jar build
docker build -t accounting-command-service .