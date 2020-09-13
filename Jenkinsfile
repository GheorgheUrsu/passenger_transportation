pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage ('Build') {

            steps {
               sh 'mvn clean'
               sh 'mvn install'
            }
        }

        stage ('Test') {
        //def mvnHome = tool name: 'maven-3', type: 'maven'
            steps {
                sh 'mvn test'
            }
        }
    }
}