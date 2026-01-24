pipeline {
    agent any

    triggers {
        githubPush()   
    }

    stages {
        stage('Cloner le repo') {
            steps {
                git url: 'https://github.com/hind0074/Gestion-fil-d-attente-.git', branch: 'develop'
            }
        }

        stage('Préparer le dossier build') {
            steps {
                bat 'if not exist build mkdir build'
            }
        }

        stage('Compiler le projet') {
            steps {
                bat 'dir /s /b src\\*.java > sources.txt && javac -d build @sources.txt'
            }
        }

        stage('Tests unitaires') {
            steps {
                echo 'Pas de tests unitaires configurés pour ce projet.'
            }
        }

        stage('Générer le package') {
            steps {
                bat 'jar cvf app.jar -C build .'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat 'sonar-scanner -Dsonar.projectKey=GestionFilAttente -Dsonar.sources=src'
                }
            }
        }
    }

    post {
        success {
            echo 'Build et analyse terminés avec succès !'
        }
        failure {
            echo 'Échec du build ou des tests.'
        }
    }
}
