const authTypeDefs = require('./auth_type_defs');
const productTypeDefs = require('./product_type_defs');
const userProductTypeDefs = require('./userProduct_type_defs');

//Se unen
const schemasArrays = [authTypeDefs, productTypeDefs, userProductTypeDefs];

//Se exportan
module.exports = schemasArrays;