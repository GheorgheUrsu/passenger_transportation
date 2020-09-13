pipeline {
    agent any

    stages {
       stage ('Test') {
           steps {
               withMaven(maven : 'maven-3'){
                    bat 'mvn test'
               }
           }
       }

       stage ('Build JAR') {
            steps {
               withMaven(maven : 'maven-3'){
                    bat 'mvn -DskipTests -B clean package'
               }
            }
        }
    }
}