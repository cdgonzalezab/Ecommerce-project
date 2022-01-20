const authResolver        = require('./auth_resolver')
const accountResolver     = require('./account_resolver')
const transactionResolver = require('./transaction_resolver')
const lodash              = require('lodash')

const resolvers = lodash.merge(authResolver, accountResolver, transactionResolver);
module.exports = resolvers;