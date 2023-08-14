from io import StringIO
import requests
import csv
from django.http import HttpResponse
from rest_framework.response import Response
from rest_framework.views import APIView
from http import HTTPStatus
from core.global_manager import GlobalManager
from rest_framework import status
import json


class WildFireFilterListAPIView(APIView):
    """Generates list of filters that will be displayed on the frontend."""
    def get(self, request):
        GEOGRAPHIC_DESCRIPTION = []
        FIRE_CAUSE = []
        FIRE_STATUS = []
        response = requests.get(GlobalManager().open_map_api)

        call_count = getattr(request, 'call_count', 0)

        if response.status_code == 200:
            data = response.json()
            features = data.get('features')
            for feature in features:
                GEOGRAPHIC_DESCRIPTION.append(feature.get('properties').get('GEOGRAPHIC_DESCRIPTION'))
                FIRE_CAUSE.append(feature.get('properties').get('FIRE_CAUSE'))
                FIRE_STATUS.append(feature.get('properties').get('FIRE_STATUS'))

            GEOGRAPHIC_DESCRIPTION = list(set(GEOGRAPHIC_DESCRIPTION))
            FIRE_CAUSE = list(set(FIRE_CAUSE))
            FIRE_STATUS = list(set(FIRE_STATUS))


            return Response({
                'geographic_description': GEOGRAPHIC_DESCRIPTION,
                'fire_cause': FIRE_CAUSE,
                'fire_status': FIRE_STATUS,
            }, status=HTTPStatus.OK)


        else:
            return Response({"error": "API request failed"}, status=500)


class WildFireAPIView(APIView):
    """Generates list of filtered BC Wildfire data"""
    def get(self, request):
        response = requests.get(GlobalManager().open_map_api)

        call_count = getattr(request, 'call_count', 0)

        if response.status_code == 200:
            data = response.json()
            features = data.get('features')
            new_features_2023 = []
            filtered_features_2023 = []
            query_string = None

            for feature in features:
                if feature.get('properties').get('FIRE_YEAR') >= 2023:
                    new_features_2023.append(feature)

            if request.query_params.get("fire_cause"):
                query_string = "fire_cause"
            elif request.query_params.get("fire_status"):
                query_string = "fire_status"
            elif request.query_params.get("geographic_description"):
                query_string = "geographic_description"

            if len(request.query_params) == 0:


                return Response({'features':features})

            if query_string is not None:
                for new_feature_2023 in new_features_2023:
                    query_value = new_feature_2023.get('properties').get(query_string.upper())
                    if query_value == request.query_params.get(query_string):
                        filtered_features_2023.append(new_feature_2023)

                print("Call Count = ", call_count)
                return Response(filtered_features_2023, status=HTTPStatus.OK)

        return Response({"error": "API request failed"}, status=500)















