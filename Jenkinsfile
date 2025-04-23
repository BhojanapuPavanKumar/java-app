pipeline {
    agent any

    environment {
        GITHUB_TOKEN = credentials('github-token')  // optional for other API use
    }

    stages {
        stage('Clone Repo') {
            steps {
                git url: 'https://github.com/BhojanapuPavanKumar/java-app.git',
                    credentialsId: 'github-token'
            }
        }

        stage('Build with Maven') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-car-game .'
            }
        }

        stage('Deploy App') {
            steps {
                script {
                    // Stop old container if running (optional cleanup)
                    sh 'docker stop java-car-game-container || true'
                    sh 'docker rm java-car-game-container || true'

                    // Run new container on port 8081 (since 8080 is used by Jenkins)
                    sh 'docker run -d --name java-car-game-container -p 8081:8080 java-car-game'
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline execution completed.'
        }
    }
}
