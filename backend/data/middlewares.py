class APICallCounterMiddleWare:
    def __init__(self, get_response):
        self.get_response = get_response
        self.call_count = 0

    def __call__(self,request):
        self.call_count += 1
        response = self.get_response(request)
        print("The API has been called:",self.call_count,"times")
        return response
        