def gitVersion
pipeline {
  agent {
    docker {
      image 'hseeberger/scala-sbt:11.0.11_1.5.3_2.13.6'
    }
  }
  environment {
    GITHUB_TOKEN = credentials('sbt-publisher-token')
    GH_TOKEN = credentials('github-api')
    SBT_OPS = '-Dsbt.global.base=.sbt -Dsbt.boot.directory=.sbt -Dsbt.ivy.home=.ivy2 -Dlocal=false'
    MONGO_URL = credentials('mongo-connector-cluster')
  }
  parameters {
    choice(
      name: 'VersionType',
      choices: "minor\nmajor\nhotfix",
      description: 'What type of version to build.'
    )
  }
  options {
    ansiColor('xterm')
  }
  stages {
    stage('Compile project') {
      steps {
        script {
          sh 'sbt $SBT_OPS clean compile'
        }
      }
    }
    stage('Version project') {
      when {
        branch 'master'
      }
      steps {
        script {
          build job: 'operations/create-a-release', parameters: [string(name: 'Project', value: 'log-encoding'), string(name: 'Type', value: params.VersionType)]
          gitVersion = sh(
            script: '''
              JQ=./jq
              curl https://stedolan.github.io/jq/download/linux64/jq > $JQ && chmod +x $JQ
              curl -H "Accept: application/vnd.github.manifold-preview" -H 'Authorization: token '$GH_TOKEN'' -s 'https://api.github.com/repos/cjww-development/log-encoding/releases/latest' | ./jq -r '.tag_name'
            ''',
            returnStdout: true
          ).trim()
        }
      }
    }
    stage('Publish artefact') {
      when {
        branch 'master'
      }
      steps {
        script {
          sh "sbt $SBT_OPS -Dversion=$gitVersion publish"
        }
      }
    }
  }
  post {
    always {
      cleanWs()
    }
  }
}