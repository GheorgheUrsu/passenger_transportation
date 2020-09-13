pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage ('Build') {
         //Get maven home path
        def mvnHome = tool name: 'maven-3', type: 'maven'
            steps {
               sh "${mvnHome}/bin/mvn clean"
               sh "${mvnHome}/bin/mvn package"
            }
        }

        stage ('Test') {
        def mvnHome = tool name: 'maven-3', type: 'maven'
            steps {
                sh "${mvnHome}/bin/mvn test"
            }
        }
    }
}