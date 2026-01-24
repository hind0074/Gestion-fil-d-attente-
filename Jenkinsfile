pipeline {
    agent any

    triggers {
        githubPush()   
    }

    stages {

        stage('Cloner le projet') {
            steps {
                git url: 'https://github.com/hind0074/Gestion-fil-d-attente-.git', branch: 'develop'
            }
        }

        stage('Compiler le projet') {
            steps {
                // Compile tous les fichiers Java du dossier src
                bat 'javac -d build src\\**\\*.java'
            }
        }

        stage('Tests unitaires') {
            steps {
                // Si tu as des tests JUnit, tu peux les lancer avec java -cp
                // Sinon, tu peux ignorer ou mettre un echo
                echo 'Pas de tests unitaires configurés pour ce projet.'
            }
        }

        stage('Générer le package') {
            steps {
                // Crée un fichier JAR à partir des classes compilées
                bat 'jar cvf app.jar -C build .'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    // Analyse du code source avec SonarScanner (pas Maven)
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
