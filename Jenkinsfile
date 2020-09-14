pipeline {
    agent any

    stages {
       stage("Prepare environment variables") {
                   steps {
                       script {
                           env.appName = readMavenPom().getArtifactId()
                           env.appVersion = readMavenPom().getVersion()
                           echo "App name: ${appName} \nApp version: ${appVersion}"
                       }
                   }
               }

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