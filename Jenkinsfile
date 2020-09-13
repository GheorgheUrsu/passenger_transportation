pipeline {
    agent any
    enviroment {
    def mvnHome = tool name: 'maven-3', type: 'maven'
    }

    stages {

       stage ('Test') {
           steps {
                echo "JAVA TEST"
                sh "${mvnHome}/bin/mvn test"
           }
       }

       stage ('Build JAR') {
            steps {
               echo "Building JAR"
               sh "${mvnHome}/bin/mvn -Dskiptests -B clean package"
            }
        }
    }
}