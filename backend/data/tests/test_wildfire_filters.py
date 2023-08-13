from django.urls import reverse
from rest_framework import status
from rest_framework.test import APITestCase

class WildFireFilterListAPIView(APITestCase):
    url = r"https://openmaps.gov.bc.ca/geo/pub/ows?service=WFS&version=2.0.0&request=GetFeature&typeName=pub:WHSE_LAND_AND_NATURAL_RESOURCE.PROT_CURRENT_FIRE_PNTS_SP&outputFormat=application%2Fjson"

    def setUp(self):
        # Create test data or set up the database for testing
        pass

    def test_filter_wildfire(self):
        url = reverse(url)
        response = self.client.get(url)
        self.assertEqual(response.status_code, status.HTTP_200_OK)