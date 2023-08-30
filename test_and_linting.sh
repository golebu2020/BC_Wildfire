export TAG=$1
sh " docker-compose run web sh -c 'python manage.py wait_for_db && python manage.py test' " 