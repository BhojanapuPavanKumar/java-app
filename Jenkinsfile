pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('github-token')  // This can be used in other stages, like Docker auth
    }

    stages {
        stage('Clone Repo') {
            steps {
                git url: 'https://github.com/BhojanapuPavanKumar/java-app',
                    credentialsId: 'github-token'
            }
        }

        stage('Checkout') {
            steps {
                script {
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-car-game .'
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Example using stored credentials (if you store Docker Hub creds in Jenkins too)
                    // withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    //     sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
                    //     sh 'docker push your-dockerhub-username/java-car-game'
                    // }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8081:8080 java-car-game'
            }
        }
    }

    post {
        always {
            sh 'docker system prune -f'
        }
    }
}
