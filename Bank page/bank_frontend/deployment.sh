sudo heroku login
sudo heroku create misiontic-2021-frontend
sudo heroku container:login
sudo heroku container:push web --app misiontic-2021-frontend
sudo heroku container:release web --app misiontic-2021-frontend