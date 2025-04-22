pipeline {
    agent any

    tools {
        jdk 'Java-17' // Or any JDK you have installed in Jenkins
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-username/java-app.git'
            }
        }

        stage('Compile') {
            steps {
                dir('src') {
                    sh 'javac Game1.java reindere.java'
                }
            }
        }

        stage('Run Game') {
            steps {
                echo '⚠️ Running GUI code in Jenkins might fail on headless servers!'
                dir('src') {
                    sh 'java Game1 & sleep 10' // Just run for a while then kill
                }
            }
        }
    }
}
