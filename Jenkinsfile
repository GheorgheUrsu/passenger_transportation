pipeline {
    agent any
    environment {
        mvnHome = tool('maven-3')
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