pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                script {
                    // Configurar Maven y compilar el proyecto
                    sh 'mvn clean install'
                }
            }
        }
    }
}
