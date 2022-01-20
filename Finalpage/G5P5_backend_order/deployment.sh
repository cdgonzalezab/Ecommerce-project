./mvnw package
sudo heroku login
sudo heroku container:login
sudo heroku container:push web --app ecommerce-product-ms
sudo heroku container:release web --app ecommerce-product-ms
