pipeline {
    agent any
    def mvnHome = tool name: 'maven-3', type: 'maven'

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
               sh "mvn -Dskiptests -B clean package"
            }
        }
    }
}