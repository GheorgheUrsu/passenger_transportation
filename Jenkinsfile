pipeline {
    agent any

    stages {

       stage ('Test') {
       def mvnHome = tool name: 'maven-3', type: 'maven'
           steps {
                echo "JAVA TEST"
                sh "${mvnHome}/bin/mvn test"
           }
       }

       stage ('Build JAR') {
       def mvnHome = tool name: 'maven-3', type: 'maven'
            steps {
               echo "Building JAR"
               sh 'mvn -Dskiptests -B clean package'
            }
        }
    }
}