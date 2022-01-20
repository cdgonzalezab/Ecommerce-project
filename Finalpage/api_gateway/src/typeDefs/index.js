const authTypes        = require("./auth_type_defs")
const accountTypes     = require('./account_type_defs')
const transactionTypes = require('./transaction_type_defs')

const schemaArrays = [authTypes, accountTypes, transactionTypes]
module.exports     = schemaArrays