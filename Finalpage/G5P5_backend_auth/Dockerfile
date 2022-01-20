FROM python:3.8
ENV PYTHONUNBUFFERED 1
RUN mkdir /users
WORKDIR /users
ADD . /users/
RUN pip install -r requirements.txt
EXPOSE 8080

#CMD ["node", "src/index.js"]
CMD python manage.py makemigrations && python manage.py migrate && python manage.py runserver 0.0.0.0:$PORT

#Guía práctica

#FROM node:14
#WORKDIR /usr/src/app
#COPY package*.json ./
#RUN npm install
#COPY . .
#EXPOSE 4000
#CMD [ "node", "src/index.js" ]

