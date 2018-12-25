# Docker Commands

List of containers in the local repository:
```sh
$ docker images
```

Run a local container:
```sh
$ docker run -p 8080:8080 ijgomez/course-docker-microservice
```

List of container is running:
```sh
$ docker ps
$ docker ps -a
```

Remove container images:
```sh
$ docker rmi <image> <image> <image>
```

Start one or more stopped containers:
```sh
$ docker start ijgomez/course-docker-microservice
```

.
```sh
$ docker-compose up -d
```

.
```sh
$ docker-compose -d docker-compose.yml up -d
```

.
```sh
$ set COMPOSE_CONVERT_WINDOWS_PATHS=1
$ docker-compose -f docker-compose.yml up -d --scale microservice=3
$ curl http://localhost/test
```
