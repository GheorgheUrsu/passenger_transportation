pipeline {
    agent any

    tools{
        maven "3.6.2"
    }

    stages {
        stage("Read from Maven POM"){
            steps{

                script{
                    projectArtifactId = readMavenPom().getArtifactId()
                    projectVersion = readMavenPom().getModelVersion()
                }

                echo "Building ${projectArtifactId}:${projectVersion}"
            }
        }
        stage("Test"){
            steps {
                bat "mvn -version"
                bat "mvn test"
            }
        }
        stage("Build JAR file"){
            steps{
                bat "mvn -Dskiptests -B clean package"
            }
        }
        stage("Build image"){
            steps {
                echo "Building service image and pushing it to DockerHub"
                    withCredentials([usernamePassword(credentialsId: 'docker-credentials', usernameVariable: "dockerLogin",
                        passwordVariable: "dockerPassword")]) {

                            bat "docker login -u ${dockerLogin} -p ${dockerPassword}"
                            bat "docker image build -t ${dockerLogin}/${projectVersion} ."
                            bat "docker push ${dockerLogin}/${projectVersion}"
                        }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}