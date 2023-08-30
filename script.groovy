#!/usr/bin/env groovy

/* groovylint-disable-next-line MethodReturnTypeRequired */
def testing(){
    echo "hey"
}

def incrementVersion(){
    def versionLatest = readFile("${WORKSPACE}/version.xml")
    matcher = versionLatest.split(",")
    major = matcher[0]
    minor = matcher[1]
    patch = matcher[2]
    echo "${major}.${minor}.${patch}"
}

def buildPush(){
    // echo "${env.major}"
    // echo "${env.minor}"
    // echo "${env.patch}"
    // sh "docker-compose build --build-arg TAG=${TAG}"
    // sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test --build-arg TAG=1.0.0' "
}

return this