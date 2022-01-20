const { RESTDataSource } = require('apollo-datasource-rest');
const serverConfig = require('../server');

class UserProductAPI extends RESTDataSource {
    constructor () {
        super();
        this.baseURL = serverConfig.product_api_url;
    }

    async createItem(item) {
        item = new Object(JSON.parse(JSON.stringify(item)));
        return await this.post('/item',item);
    }

    async getItem(userId, productId) {
        return await this.get(`/item/${userId}/${productId}`);
    }

    async getItemsByUserId(userId){
        return await this.get(`/items/${userId}`);
    }

    async updateItem(item) {
        item = new Object(JSON.parse(JSON.stringify(item)));
        return await this.put(`/item`,item);
    }

    async deleteItem(userId, productId){
        return await this.delete(`/item/${userId}/${productId}`);
    }

    async deleteItemByUserId(userId){
        return await this.delete(`/item/userId/${userId}`);
    }

    async deleteItemByProducId(productId){
        return await this.delete(`/item/userId/${productId}`);
    }

}

module.exports = UserProductAPI;