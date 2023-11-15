docker yasuki-spring | true
docker rmi spring-boot-app | true

until docker build -t yasuki-spring-image .
do
    echo "Waiting for build image yasuki-spring-image..."
    sleep 4
done

docker run -d -p 8081:8081 --name yasuki-spring yasuki-spring-image