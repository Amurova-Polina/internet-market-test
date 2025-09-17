pipeline {
    agent { label 'honor' }

    tools {
        maven 'maven-3.9.11'
        jdk 'jdk-17'
    }

    parameters {
        choice(
            name: 'GIT_BRANCH',
            choices: ['dev', 'master'],
            description: 'Ветка для сборки'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Браузер для запуска тестов'
        )

        string(
             name: 'TAG',
             defaultValue: '',
             description: 'Тег тестов, которые нужно запустить (оставьте пустым для всех)'
        )
    }

    stages {
        stage('Cleanup') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git branch: "${params.GIT_BRANCH}",
                    url: 'https://github.com/Amurova-Polina/internet-market-test.git'
            }
        }

        stage('Build & Test') {
             script {
              def tagOption = params.TAG?.trim() ? "-DincludeTags=${params.TAG}" : ""
              bat "mvn clean compile test -Dbrowser=${params.BROWSER} ${tagOption}"
              }
        }

        stage('Allure Report') {
            steps {

            }
        }
    }

post {
    always {
        allure([
            results: [[path: 'target/allure-results']]
        ])
        junit 'target/surefire-reports/*.xml'
    }
    success {
        echo '===========Победа-победа!==========='
    }
    failure {
        echo '===========Ну ничего страшного. Пау-пау-пау-пау==========='
    }
}
}
