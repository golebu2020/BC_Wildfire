class APICallCounterMiddleWare:
    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self,request):
        request.call_count = getattr(request, 'call_count', 0) + 1
        response = self.get_reponse(request)
        return response
    