pipeline {
    agent any


    stages {
       stage ('Test') {
           steps {
                echo "JAVA TEST"
                sh "mvn test"
           }
       }

       stage ('Build JAR') {
            steps {
               echo "Building JAR"
               sh "mvn -Dskiptests -B clean package"
            }
        }
    }
}