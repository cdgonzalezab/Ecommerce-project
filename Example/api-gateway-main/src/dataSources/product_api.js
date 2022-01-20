const { RESTDataSource } = require('apollo-datasource-rest');
const serverConfig = require('../server');

class ProductAPI extends RESTDataSource {
    constructor () {
        super();
        this.baseURL = serverConfig.product_api_url;
    }

    async createProduct(product) {
        product = new Object(JSON.parse(JSON.stringify(product)));
        return await this.post('/product',product);
    }

    async getProduct(productId) {
        return await this.get(`/product/${productId}`);
    }

    async getProducts(){
        return await this.get('/products/');
    }

    async getProductsByService(state){
        return await this.get(`/products/${state}`);
    }

    async updateProduct(productId,product) {
        product = new Object(JSON.parse(JSON.stringify(product)));
        return await this.put(`/product/${productId}`,product);
    }

    async deleteProduct(productId){
        return await this.delete(`/product/${productId}`);
    }

}

module.exports = ProductAPI;