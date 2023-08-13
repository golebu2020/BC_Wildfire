from django.urls import path, include
from .views import  WildFireFilterListAPIView
from .views import  WildFireAPIView
urlpatterns = [
    path('', WildFireFilterListAPIView.as_view(), name="filter_list"), 
    path('search/', WildFireAPIView.as_view(), name="search_data"), 
]