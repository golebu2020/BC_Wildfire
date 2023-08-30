#!/usr/bin/env groovy

/* groovylint-disable-next-line MethodReturnTypeRequired */
def testing(){
    sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test' "
}

def incrementVersion(){
    def versionLatest = readFile("${WORKSPACE}/version.xml")
    matcher = versionLatest.split(",")
    major = matcher[0]
    minor = matcher[1]
    patch = matcher[2]
    echo "The major is ${major}"
}

return this