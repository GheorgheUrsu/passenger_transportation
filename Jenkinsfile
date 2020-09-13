pipeline {
    agent any

    stages {
    def mvnHome = tool name: 'maven-3', type: 'maven'
       stage ('Test') {
           steps {
                echo "JAVA TEST"
                sh "${mvnHome}/bin/mvn test"
           }
       }

       stage ('Build JAR') {
            steps {
               echo "Building JAR"
               sh 'mvn -Dskiptests -B clean package'
            }
        }
    }
}