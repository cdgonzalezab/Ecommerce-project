const { gql } = require('apollo-server');

const authTypeDefs = gql `
    type Tokens {
        refresh: String!
        access: String!
    }

    type Access {
        access: String!
    }

    input CredentialsInput {
        username: String!
        password: String!
    }

    input SignUpInput {
        username: String!
        password: String!
        name: String!
        email: String!
        phone: String
        admin: Boolean
    }

    type UserDetail {
        id: Int!
        username: String!
        name: String!
        email: String!
        phone: String
        admin: Boolean
    }

    type Query {
        userDetailById(userId: Int): UserDetail!
        userList: [UserDetail]
    }

    type Mutation {
        signUpUser(userInput: SignUpInput!): Tokens!
        logIn(credentials: CredentialsInput!): Tokens!
        refreshToken(refresh: String!): Access!
        updateUser(userId: Int, putUser: SignUpInput!): UserDetail!
        deleteUser: String!
    }
`;

module.exports = authTypeDefs;