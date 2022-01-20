const authResolver = require('./auth_resolver');
const productResolver = require('./product_resolver');
const userProductResolver = require('./userProduct_resolver');

const lodash = require('lodash');

const resolvers = lodash.merge(authResolver, productResolver,userProductResolver);

module.exports = resolvers;