pipeline {
    agent any
    environment {
        PATH = "C:\Program Files\apache-maven-3.6.2\bin:PATH"
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