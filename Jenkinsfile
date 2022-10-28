pipeline {
    agent any
    options {
        timeout(time: 1, unit: 'HOURS')
    }

    stages {
        stage('Init') {
            steps {
                echo 'clear'
                sh 'docker-compose down'
                sh 'docker container prune -y'
                sh 'docker image prune -a -y'
            }
        }

        stage('build blog-spring') {
            steps {
                sh "./mvnw clean install"
            }
        }

        stage('build docker-compose') {
            steps {
                sh "docker-compose build"
            }
        }

        stage('deploy') {
            steps {
                sh "sudo docker-compose up"
            }
        }
    }
}
