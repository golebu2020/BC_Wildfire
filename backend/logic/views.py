import requests
from django.http import JsonResponse
from django.views import View


class FiltersAPIView(View):
    """Generates list of filters that will be displayed on the frontend."""
    GEOGRAPHIC_DESCRIPTION = []
    FIRE_CAUSE = []
    FIRE_STATUS = []
    def get(self, request):
        response = requests.get(r"https://openmaps.gov.bc.ca/geo/pub/ows?service=WFS&version=2.0.0&request=GetFeature&typeName=pub:WHSE_LAND_AND_NATURAL_RESOURCE.PROT_CURRENT_FIRE_PNTS_SP&outputFormat=application%2Fjson")
        
        if response.status_code == 200:
            data = response.json()
            features = data.get('features')
            for feature in features:
                
                self.GEOGRAPHIC_DESCRIPTION.append(feature.get('properties').get('GEOGRAPHIC_DESCRIPTION'))
                self.FIRE_CAUSE.append(feature.get('properties').get('FIRE_CAUSE'))
                self.FIRE_STATUS.append(feature.get('properties').get('FIRE_STATUS'))

            self.GEOGRAPHIC_DESCRIPTION = list(set(self.GEOGRAPHIC_DESCRIPTION))
            self.FIRE_CAUSE = list(set(self.FIRE_CAUSE))
            self.FIRE_STATUS = list(set(self.FIRE_STATUS))
            return JsonResponse({
                'geographic_description': self.GEOGRAPHIC_DESCRIPTION,
                'fire_cause': self.FIRE_CAUSE,
                'fire_status': 'self.FIRE_STATUS',
            })
        else:
            return JsonResponse({"error": "API request failed possibly due to bad network"}, status=500)
        

            
