from django.urls import path, include
from .views import  WildFireFilterListAPIView
from .views import  WildFireAPIView
urlpatterns = [
    path('wildfire/', WildFireFilterListAPIView.as_view(), name="filter_list"), 
    path('wildfire/search/', WildFireAPIView.as_view(), name="search_data"), 
]