//pipeline {
//    agent any
//
//    stages {
//       stage ('Test') {
//           steps {
//                echo "JAVA TEST"
//                bat "mvn test"
//           }
//       }
//
//       stage ('Build JAR file'){
//            steps {
//                echo "Building JAR file"
//                bat "mvn -Dskiptests -B clean package"
//            }
//       }
//
//       stage ("Build docker image ") {
//            steps {
//            echo "Building service docker image and push it on DockerHub"
//            withCredentials([usernamePassword(credentialsId: '	docker-credentials', usernameVariable: "dockerLogin",
//                                passwordVariable: "dockerPassword")]) {
//                       bat "docker login -u ${dockerLogin} -p ${dockerPassword}"
//                       bat "docker network create my-network"
//                       bat "docker container run --name mysqldb --network my-network -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=passengers_db -d mysql"
//                }
//            }
//       }
//    }
//}

pipeline {
    agent any

    tools{
        maven "3.6.2"
    }

    stages {
        stage("Read from Maven POM"){
            steps{
                bat "mvn -N help:effective-pom -Doutput=target/pom-effective.xml"

                script{
                    pom = readMavenPom(file: 'target/pom-effective.xml')
                    projectArtifactId = pom.getArtifactId()
                    projectGroupId = pom.getGroupId()
                    projectVersion = pom.getVersion()
                    projectName = pom.getName()
                }

                echo "Buildin ${projectArtifactId}:${projectVersion}"
            }
        }
        stage("Test"){
            steps {
                bat "mvn -version"
                bat "mvn test"
            }
        }
        stage("Build JAR file"){
            steps{
                bat "mvn -Dskiptests -B clean package"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}