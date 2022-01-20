sudo heroku login
sudo heroku container:login
sudo heroku container:push web --app ecommerce-auth-ms
sudo heroku container:release web --app ecommerce-auth-ms