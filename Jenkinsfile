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
                bat "mvn install -Dmaven.test.skip=true"
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
        stage("Deploy"){
            steps{
                bat "docker-compose --file docker-compose.yml up --detach"
                    timeout(30)
                        waitUntil {
                            script {
                              def r = bat script: "curl --silent --output /dev/null http://localhost:8080/api/v1/routes", returnStatus: true
                              return (r == 0);
                            }
                        }
                    }
                echo "Server is fully up and running"
            }
        }
        stage("Newman Tests"){
            steps{
                echo "Running newman tests"
                bat "newman run ./newman/newman_test.json --reporters cli,json --reporter-junit-export newman/report.xml"
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
