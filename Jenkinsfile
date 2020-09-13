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
            steps {
                sh 'mvn test'
            }
        }
    }
}