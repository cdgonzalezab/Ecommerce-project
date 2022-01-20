const { gql } = require('apollo-server');

const productTypeDefs = gql `
    type UserProduct {
        id: Int!
        userId: Int!
        productId: Int!
        quantity: Int!
        dateModified: String!
    }

    input inUserProduct {
        productId: Int!
        quantity: Int!
    }

    extend type Query {
        getUserProductsByUserId(id: Int!): [UserProduct]
        getUserProduct(userId: Int!, productId: Int!): UserProduct
    }
    
    extend type Mutation {
        createUserProduct(inputItem: inUserProduct!): UserProduct
        updateUserProduct(inUpdateItem: inUserProduct!): UserProduct!
        deleteUserProduct(productId: Int!): String!
    }
`;
module.exports = productTypeDefs;