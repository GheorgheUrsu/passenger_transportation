pipeline {
    agent any

     environment {
            MAVEN_HOME = tool('M3')
        }

    stages {
        stage ('Build') {
            steps {
               sh '${MAVEN_HOME}/bin/mvn -B verify'
            }
        }
    }
}