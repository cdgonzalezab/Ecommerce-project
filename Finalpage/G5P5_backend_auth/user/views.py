from django.conf import settings
from rest_framework.response import Response
from rest_framework import views, status, generics
from user.serializers import UserSerializer
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer
from rest_framework_simplejwt.backends import TokenBackend
from rest_framework_simplejwt.views import TokenVerifyView
from rest_framework_simplejwt.exceptions import TokenError, InvalidToken
from rest_framework_simplejwt.serializers import TokenVerifySerializer
from user.models import User


class UserAPIView(views.APIView):
    def post(self, request, *args, **kwargs):
        serializer = UserSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()

        tokenData = {"username": request.data["username"],
                     "password": request.data["password"]}

        try:
            tokenSerializer = TokenObtainPairSerializer(data=tokenData)
            tokenSerializer.is_valid(raise_exception=True)
            return Response(tokenSerializer.validated_data, status=status.HTTP_201_CREATED)

        except Exception as e:
            print(e)
            return Response('Error en la generación del token', status=status.HTTP_500_INTERNAL_SERVER_ERROR)

# User Detail View

class UserDetailView(generics.RetrieveAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def get(self, request, *args, **kwargs):
        return super().get(request, *args, **kwargs)


class UserUpdateView(generics.UpdateAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def update(self, request, *args, **kwargs):
        return super().update(request, *args, **kwargs)


class UserDeleteView(generics.DestroyAPIView):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def delete(self, request, *args, **kwargs):
        return super().destroy(request, *args, **kwargs)

# Verify Token View

class VerifyTokenView(TokenVerifyView):
    def post(self, request, *args, **kwargs):
        serializer = TokenVerifySerializer(data=request.data)
        token_backend = TokenBackend(
            algorithm=settings.SIMPLE_JWT['ALGORITHM'])

        try:
            serializer.is_valid(raise_exception=True)
            token_data = token_backend.decode(
                request.data['token'], verify=False)
            serializer.validated_data['user_id'] = token_data['user_id']
        except TokenError as e:
            raise InvalidToken(e.args[0])
        except Exception as e:
            print(e)

        return Response(serializer.validated_data, status=status.HTTP_200_OK)
