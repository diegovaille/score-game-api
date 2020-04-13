# Game Score API

## Setup

Install docker image of mySql with the below command:

```
docker run -p 3306:3306 --name score-mysql -e MYSQL_ROOT_PASSWORD=root -d mysql/mysql-server
```

Connect to MySQL Database with this command:

```
docker exec -it score-mysql mysql -u root -p
```

Use the password: root

Create the database with the following command:

```
CREATE DATABASE game;
```

Create the admin user on MySQL for running flyway and the Spring application:

```
CREATE USER 'admin'@'%' IDENTIFIED WITH mysql_native_password BY 'admin';
```

Grant access permissions to the admin user with command below:

```
grant all on *.* to 'admin'@'%';
```

## How to run

Run the Command below to start **score-api** container.

```
mvn clean package docker:build docker:run
```
