const productResolver = {
    Query: {
        productById: (_,{id}, {dataSources}) => {
            return dataSources.productAPI.getProduct(id)
        },

        products: (_,{},{dataSources}) => {
            return dataSources.productAPI.getProducts();
        },

        productsByService: (_, {state}, {dataSources}) => {
            return dataSources.productAPI.getProductsByService(state);
        }
    },

    Mutation: {
        createProduct: async(_,{inputProduct}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken)
                if (adm.admin == true) 
                    return await dataSources.productAPI.createProduct(inputProduct);
                else 
                    return null
            }
            else 
                return null;
        },

        updateProduct: async(_,{id, product}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken)
                if (adm.admin == true) 
                    return await dataSources.productAPI.updateProduct(id,product)
                else
                    return null
            }
                
        },

        deleteProduct: async(_,{id}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken)
                if (adm.admin == true){
                    await dataSources.userProductAPI.deleteItemByProducId(id);
                    return await dataSources.productAPI.deleteProduct(id)
                }
                else
                    return null
            }
            else
                return null
        },

    }
};

module.exports = productResolver;