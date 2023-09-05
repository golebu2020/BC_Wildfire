from django.db import models

class Person(models.Model):
    firstName = models.CharField(max_length=200)
    lastName = models.CharField(max_length=200)
    age = models.IntegerField()

    def __str__(self):
        return self.name
