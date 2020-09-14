pipeline {
    agent any

    stages {
       stage ('Test') {
           steps {
                echo "JAVA TEST"
                bat "mvn test"
           }
       }

       stage ('Build JAR file'){
            steps {
                echo "Building JAR file"
                bat "mvn -Dskiptests -B clean package"
            }
       }

       stage ("Build DB dockerImage ") {
            steps {
            echo "Building docker mySQL image"
            withCredentials([usernamePassword(credentialsId: 'dockerCredentials', usernameVariable: "dockerLogin",
                                passwordVariable: "dockerPassword")]) {
                       bat "docker login -u ${dockerLogin} -p ${dockerPassword}"
                       bat "docker network create my-network"
                       bat "docker container run --name mysqldb --network my-network -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=passengers_db -d mysql:8"
                }
            }
       }
    }
}