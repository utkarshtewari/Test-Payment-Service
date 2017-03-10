rm -fr build
mkdir build
cp ../target/payments-service-0.0.1-SNAPSHOT.jar build
docker build -t payments-service .