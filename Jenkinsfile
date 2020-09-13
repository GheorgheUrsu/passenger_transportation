pipeline {
    agent any

    stages {
        stage("Prepare enviroment variable") {
            steps {
                 script {
                    env.appName = readMavenPom().getArtifactId()
                    env.appVersion = readMavenPom().getVersion()
                    echo "App name: ${appName} \nApp version: ${appVersion}"
                }
            }
        }
        stage("Testing"){
            steps {
                echo "Java Testing"
                sh "mvn test"
            }
        }
    }
}
