rm -fr build
mkdir build
cp ../target/screening-service-0.0.1-SNAPSHOT.jar build
docker build -t screening-service .