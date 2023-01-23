pipeline {
    agent any
    tools{
        maven "maven3"
    }

     environment{
        registryName = "acranoop123"
        registryCredential = "ACR"
        registryUrl = "acranoop123.azurecr.io"
        dockerImage = ""
    }

    stages {

        stage("Build"){
            steps {
                sh "mvn clean install"
            }
        }

        stage("Build Image"){
            steps{
                script{
                     dockerImage = docker.build registryName
                }
            }
        }

        stage("Push Image"){
            steps{
                script{
                     docker.withRegistry( "http://${registryUrl}", registryCredential ) {
                     dockerImage.push()
                     }
                }
            }
        }

        stage("Deploy to K8S"){
            steps{
                withKubeConfig(caCertificate: '', clusterName: '', contextName: '', credentialsId: 'K8S', namespace: '', serverUrl: '') {
                 sh "kubectl apply -f deploy.yaml"
                }
            }
        }
    }
}
