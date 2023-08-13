from django.urls import path, include
from .views import  WildFireFilterListAPIView
from .views import  WildFireAPIView
from .views import JSONToCSVConverter
urlpatterns = [
    path('', WildFireFilterListAPIView.as_view(), name="filter_list"), 
    path('search/', WildFireAPIView.as_view(), name="search_data"), 
    path('convert-json-to-csv/', JSONToCSVConverter.as_view(), name='convert-json-to-csv'),
]