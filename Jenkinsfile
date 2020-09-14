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
            withCredentials([usernamePassword(credentialsId: '	docker-credentials', usernameVariable: "dockerLogin",
                                passwordVariable: "dockerPassword")]) {
                       bat "docker login -u ${dockerLogin} -p ${dockerPassword}"
                       bat "docker network create my-network"
                       bat "docker container run --name mysqldb --network my-network -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=passengers_db -d mysql:8"
                }
            }
       }

       stage ("Build my-api image") {
            steps {
                withCredentials([usernamePassword(credentialsId: '	docker-credentials', usernameVariable: "dockerLogin",
                                                passwordVariable: "dockerPassword")]) {
                        echo "Creating image from Dockerfile"
                        bat "docker image build -f Dockerfile -t my-image ."

                        //echo "Creating image from Dockerfile"
                       // bat "docker login"
                        //bat "docker push 23082018/my-image"

                        echo "Creating container"
                        bat "docker container run --network my-network  --name demo-container -p 8282:8282 -d my-image"

                        echo "Server is up on PORT: 8282"
                }
            }
       }
    }
}