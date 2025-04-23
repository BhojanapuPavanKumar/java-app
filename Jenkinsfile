pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('github-token')  // Your GitHub token
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    // Checkout the GitHub repository
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    // Build the project using Maven
                    sh 'mvn clean install'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    sh 'docker build -t java-car-game .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Log in to Docker Hub (if needed)
                    // sh 'docker login -u <username> -p <password>'

                    // Push Docker image to Docker Hub (optional)
                    // sh 'docker push <your-image-name>'
                }
            }
        }

        stage('Deploy') {
            steps {
                script {
                    // You can add deployment scripts here (e.g., Docker run or AWS EC2 deploy)
                    // For now, let's assume Docker is used to deploy
                    sh 'docker run -d -p 8080:8080 java-car-game'
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images (optional)
            sh 'docker system prune -f'
        }
    }
}
