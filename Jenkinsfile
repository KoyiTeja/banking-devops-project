stage('Docker Push') {
    steps {
        sh 'docker push tejakoyi/banking-app:1.0'
    }
}

stage('Kubernetes Deploy') {
    steps {
        sh '''
            kubectl set image deployment/banking-app \
            banking-app=tejakoyi/banking-app:1.0 \
            -n banking
        '''
    }
}

stage('Kubernetes Rollout Status') {
    steps {
        sh '''
            kubectl rollout status deployment/banking-app \
            -n banking
        '''
    }
}