pipeline {
    agent any
      agent { docker 'maven:3-alpine' }

    stages {
       stage ('Test') {
           steps {
                echo "JAVA TEST"
                sh "mvn test"
           }
       }
    }
}