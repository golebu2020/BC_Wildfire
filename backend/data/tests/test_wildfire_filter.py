from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase
from unittest.mock import patch
from core.global_manager import GlobalManager

class WildFireFilterListAPIViewTest(APITestCase):
    """Testing API with some test cases"""

    def test_wildfire_filter_list(self):
        response = self.client.get(GlobalManager().filter_list_api)
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_wildfire_queried_with_fire_cause(self):
        response = self.client.get(GlobalManager().search_api + "?fire_cause=Person")
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_wildfire_queried_with_fire_status(self):
        response = self.client.get(GlobalManager().search_api + "?fire_status=Out")
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    def test_wildfire_queried_with_fire_geographic_description(self):
        response = self.client.get(GlobalManager().search_api + "?geographic_description=Hughes Lake")
        self.assertEqual(response.status_code, status.HTTP_200_OK)

    