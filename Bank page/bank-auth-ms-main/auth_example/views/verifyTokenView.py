from django.conf                          import settings
from rest_framework                       import status
from rest_framework.response              import Response
from rest_framework_simplejwt.views       import TokenVerifyView
from rest_framework_simplejwt.backends    import TokenBackend
from rest_framework_simplejwt.exceptions  import TokenError, InvalidToken
from rest_framework_simplejwt.serializers import TokenVerifySerializer

class VerifyTokenView(TokenVerifyView):
    def post(self, request, *args, **kwargs):
        serializer    = TokenVerifySerializer(data=request.data)
        token_backend = TokenBackend(algorithm=settings.SIMPLE_JWT['ALGORITHM'])

        try:
            serializer.is_valid(raise_exception=True)
            token_data = token_backend.decode(request.data['token'], verify=False)
            serializer.validated_data["UserId"] = token_data['user_id']
        except TokenError as e:
            raise InvalidToken(e.args[0])
        except Exception as e:
            print(e)

        return Response(serializer.validated_data, status=status.HTTP_200_OK)