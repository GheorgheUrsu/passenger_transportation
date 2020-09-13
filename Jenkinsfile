pipeline {
    agent any

    stages {

       stage ('Test') {
           steps {
            def mvnHome = tool name: 'maven-3', type: 'maven'
                echo "JAVA TEST"
                sh "${mvnHome}/bin/mvn test"
           }
       }

       stage ('Build JAR') {
            steps {
             def mvnHome = tool name: 'maven-3', type: 'maven'
               echo "Building JAR"
               sh "mvn -Dskiptests -B clean package"
            }
        }
    }
}