const userProductResolver = {
    Query: {
        getUserProductsByUserId: (_,{id}, {dataSources}) => {
            return dataSources.userProductAPI.getItemsByUserId(id);
        },
        getUserProduct: (_, {userId, productId}, {dataSources}) => {
            return dataSources.userProductAPI.getItem(userId,productId);
        }  
    },

    Mutation: {
        createUserProduct: async(_,{inputItem}, {dataSources, userIdToken}) => {
             if (userIdToken != null) {
                const creatingItem = {
                    userId: userIdToken,
                    productId: inputItem.productId,
                    quantity: inputItem.quantity,
                    dateModified: (new Date()).toISOString()
                }
                return await dataSources.userProductAPI.createItem(creatingItem);
            }
            else 
                return "Problema de autentificación";
        },

        updateUserProduct: async(_,{inUpdateItem}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken)
                const creatingItem = {
                    userId: userIdToken,
                    productId: inUpdateItem.productId,
                    quantity: inUpdateItem.quantity,
                    dateModified: (new Date()).toISOString()
                }
                return await dataSources.userProductAPI.updateItem(creatingItem);
            }
            else
                return "Problema de autentificación"
                
        },

        deleteUserProduct: async(_,{productId}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                return await dataSources.userProductAPI.deleteItem(userIdToken, productId)
            }
            else
                return "Problema de autentificación"
        },

    }
};

module.exports = userProductResolver;