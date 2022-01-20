sudo heroku login
sudo heroku create ecommerce-apig5
sudo heroku container:login
sudo heroku container:push web --app ecommerce-apig5
sudo heroku container:release web --app ecommerce-apig5