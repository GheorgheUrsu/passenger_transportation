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

        stage ('Testing') {
            steps {
             echo "Running Java Tests"
             sh "mvn test"
            }

        stage ('Build') {
            steps {
                echo "Build project"
                sh "mvn -Dskiptests -B clean package"
            }
        }
    }
}