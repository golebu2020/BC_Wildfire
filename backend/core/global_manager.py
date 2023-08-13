class GlobalManager:
    _instance = None
    my_global_variable = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super(GlobalManager, cls).__new__(cls)
            cls.filter_list_api = "http://127.0.0.1:8000/api/wildfire/"
            cls.search_api = "http://127.0.0.1:8000/api/wildfire/search/" 
            cls.open_map_api = r"https://openmaps.gov.bc.ca/geo/pub/ows?service=WFS&version=2.0.0&request=GetFeature&typeName=pub:WHSE_LAND_AND_NATURAL_RESOURCE.PROT_CURRENT_FIRE_PNTS_SP&outputFormat=application%2Fjson"
        return cls._instance
