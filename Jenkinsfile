pipeline {
    agent any

    tools {
        maven "3.6.2"
    }
   //options {
   //    disableConcurrentBuilds()
   //}

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
                echo "Server is fully up and running"
            }
        }
        stage("Newman Tests"){
            steps {
                script {
                    dir("${JENKINS_HOME}/workspace/myproject") {
                    bat 'newman run "./Collections/my_collection.postman_collection.json" --reporters cli,junit,htmlextra --reporter-junit-export "newman_result.xml" --reporter-htmlextra-export "newman_result.html"'
                    junit "*.xml"
                    }
                }
            publishHTML target: [
                        allowMissing: false,
                        alwaysLinkToLastBuild: false,
                        keepAll: true,
                        reportDir: '.',
                        reportFiles: 'newman_result.html',
                        reportName: 'Newman HTML Reporter'
            }
        }
    post {
        always {
            cleanWs()
        }
    }
}
