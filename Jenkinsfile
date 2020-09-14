pipeline {
    agent any

    stages {
       stage ('Test') {
           steps {
                echo "JAVA TEST"
                bat "mvn test"
           }
       }

       stage ('Build JAR file'){
            steps {
                echo "Building JAR file"
                bat "mvn -Dskiptests -B clean package"
            }
       }
    }
}