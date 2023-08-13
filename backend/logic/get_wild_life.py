import requests


response = requests.get(r"https://openmaps.gov.bc.ca/geo/pub/ows?service=WFS&version=2.0.0&request=GetFeature&typeName=pub:WHSE_LAND_AND_NATURAL_RESOURCE.PROT_CURRENT_FIRE_PNTS_SP&outputFormat=application%2Fjson")

data = response.json()
features = data.get('features')

GEOGRAPHIC_DESCRIPTION = []
FIRE_CAUSE = []
FIRE_STATUS = []

for feature in features:
    """Generates list of filters that will be displayed on the frontend."""
    GEOGRAPHIC_DESCRIPTION.append(feature.get('properties').get('GEOGRAPHIC_DESCRIPTION'))
    FIRE_CAUSE.append(feature.get('properties').get('FIRE_CAUSE'))
    FIRE_STATUS.append(feature.get('properties').get('FIRE_STATUS'))

"""Regenerate list with unique elements."""
GEOGRAPHIC_DESCRIPTION = list(set(GEOGRAPHIC_DESCRIPTION))
FIRE_CAUSE = list(set(FIRE_CAUSE))
FIRE_STATUS = list(set(FIRE_STATUS))
    

print(len(GEOGRAPHIC_DESCRIPTION))
print(len(FIRE_CAUSE))
print(len(FIRE_STATUS))

