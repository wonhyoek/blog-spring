pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
    }
    environment {
        SOURCECODE_JENKINS_CREDENTIAL_ID = 'jenkins-privateKey'
        SOURCE_CODE_URL = 'git@github.com:wonhyoek/blog-spring.git'
        RELEASE_BRANCH = 'master'
    }
    stages {
        stage('Init') {
            steps {
                echo 'clear'
                sh 'sudo docker-compose down'
                sh 'sudo docker rm $(docker ps -aq)'
                deleteDir()
            }
        }

        stage('clone') {
            steps {
                git url: "$SOURCE_CODE_URL",
                    branch: "$RELEASE_BRANCH",
                    credentialsId: "$SOURCECODE_JENKINS_CREDENTIAL_ID"
                sh "ls -al"
            }
        }

        stage('build blog-spring') {
            steps {
                sh "./mvnw clean install"
            }
        }

        stage('build docker-compose') {
            steps {
                sh "sudo docker-compose build"
            }
        }

        stage('deploy') {
            steps {
                sh "sudo docker-compose up"
            }
        }
    }
}