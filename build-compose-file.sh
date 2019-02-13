# WARNING: This will delete all your containers and images
docker kill $(docker ps -q)
docker rm $(docker ps -a -q)
echo y | docker image prune

cd ./backend/
gradle build
cd ../
docker-compose up --build