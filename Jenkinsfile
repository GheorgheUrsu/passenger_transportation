pipeline {
    agent any

    stages {
       stage ('Test') {
           steps {
                echo "JAVA TEST"
                bat "mvn test"
           }
       }

       stage ('Build JAR'){
                echo "Building JAR file"
                bat "mvn -Dskiptests -B clean package"
       }
    }
}