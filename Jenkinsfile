pipeline {

  agent any
    
  stages {
   stage('Running Junit and Mockito tests...') {
    
    steps {
        echo 'Running JAVA tests'
        sh "mvn test"
      }
    }
  }
}
