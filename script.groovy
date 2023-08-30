#!/usr/bin/env groovy

/* groovylint-disable-next-line MethodReturnTypeRequired */
def testing(){
    sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test' "
}

def incrementVersion(){

}
return this