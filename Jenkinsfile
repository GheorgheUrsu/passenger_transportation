pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }

    stages {
    //Get maven home path
    def mvnHome = tool name: 'maven-3', type: 'maven'
        stage ('Build') {
            steps {
               sh "${mvnHome}/bin/mvn clean"
               sh "${mvnHome}/bin/mvn package"
            }
        }

        stage ('Test') {
            steps {
                sh "${mvnHome}/bin/mvn test"
            }
        }
    }
}