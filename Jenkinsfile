pipeline {
    agent any
    stages {
        stage('Gradle test') {
            steps {
                script {
                    try {
                        sh 'chmod 755 ./gradlew'
                        sh 'docker-compose -f docker-compose-dev.yml down'
                        sh 'docker-compose -f docker-compose-dev.yml up -d db migration'
                        sh './gradlew clean build'
                    } catch (e) {
                        sh 'echo Gradle test Fail!!!'
                        slackSend (channel: '#jenkins-test', color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                    }
                }
            }
        }
        stage('Dockerfile build') {
            steps {
                script {
                    try {
                        sh 'docker-compose -f docker-compose-dev.yml build'
                    } catch (e) {
                        sh 'echo Dockerfile build Fail!!!'
                        slackSend (channel: '#jenkins-test', color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                    }
                }
            }
        }
        stage('Docker-compose') {
            steps {
                script {
                    try {
                        sh 'docker-compose -f docker-compose-dev.yml up -d'
                        slackSend (channel: '#jenkins-test', color: '#00FF00', message: "SUCCESSFUL: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                    } catch (e) {
                        sh 'echo Docker-compose Fail!!!'
                        slackSend (channel: '#jenkins-test', color: '#FF0000', message: "FAILED: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]' (${env.BUILD_URL})")
                    }
                }
            }
        }
    }
}