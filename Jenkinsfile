pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }

    def mvn_version = ''

    withEnv( ["PATH+MAVEN=${tool mvn_version}/bin"] )

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