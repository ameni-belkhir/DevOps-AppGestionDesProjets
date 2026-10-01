pipeline {
    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                sh '''
                    cd backend
                    chmod +x mvnw
                    ./mvnw clean package -DskipTests
                '''
            }
        }

        stage('Build Docker Images') {
            steps {
                sh '''
                    docker build -t devops-backend:latest ./backend
                    docker build -t devops-frontend:latest ./frontend
                '''
            }
        }

        stage('Verify Docker Images') {
            steps {
                sh '''
                    docker images | grep devops
                '''
            }
        }
    }

    post {
        success {
            echo 'Pipeline terminé avec succès.'
        }

        failure {
            echo 'Pipeline échoué.'
        }
    }
}
