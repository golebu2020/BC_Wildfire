from django.urls import path, include
from .views import  WildFireFilterListAPIView
from .views import  WildFireAPIView
from .views import downloadCSV
urlpatterns = [
    path('', WildFireFilterListAPIView.as_view(), name="filter_list"), 
    path('search/', WildFireAPIView.as_view(), name="search_data"), 
    path('csv/', downloadCSV.as_view(), name='convert_json_to_csv'),
]