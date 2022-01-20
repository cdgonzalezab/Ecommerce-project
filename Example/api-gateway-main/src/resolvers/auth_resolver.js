const userResolver = {
    Query: {
        userDetailById: async(_, { userId }, { dataSources, userIdToken }) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken);
                if ((userId != null) && (adm.admin == true))
                    return await dataSources.authAPI.getUser(userId);
                else
                    return await dataSources.authAPI.getUser(adm.id);
            }
            else return null
        },
        userList: async (_,{}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken)
                if (adm.admin == true) 
                    return await dataSources.authAPI.getAllUser();
                else return null
            }
            else 
                return null;
        }
    },

    Mutation: {
        signUpUser: async(_, { userInput }, {dataSources}) => {
            const authInput = {
                username: userInput.username,
                password: userInput.password,
                name: userInput.name,
                email: userInput.email,
                phone: parseInt(userInput.phone),
                admin: userInput.admin
            }
            return await dataSources.authAPI.createUser(authInput);
        },


        logIn: (_,{credentials}, {dataSources}) => {
            return dataSources.authAPI.authRequest(credentials);
        },
        refreshToken: (_,{refresh}, {dataSources}) => {
            return dataSources.authAPI.refreshToken(refresh)
        },

        updateUser: async(_,{userId,putUser}, {dataSources, userIdToken}) => {
            if (userIdToken != null){
                adm = await dataSources.authAPI.getUser(userIdToken)
                if (adm.admin == true){
                    if (userId == null)
                        return await dataSources.authAPI.updateUser(adm.id, putUser);
                    else
                        return await dataSources.authAPI.updateUser(userId, putUser);
                }
                else {
                    const updateInput = {
                        username: putUser.username,
                        password: putUser.password,
                        name: putUser.name,
                        email: putUser.email,
                        phone: parseInt(putUser.phone),
                    }
                    return await dataSources.authAPI.updateUser(adm.id, updateInput);
                }
            }
            else 
                return null;
        },

        deleteUser: async(_,{},{dataSources, userIdToken}) => {
            if (userIdToken != null){
                usr = await dataSources.authAPI.getUser(userIdToken)
                await dataSources.userProductAPI.deleteItemByUserId(usr.id);
                return await dataSources.authAPI.deleteUser(usr.id);
            }
            else 
                return null;
        }
    }
};
module.exports = userResolver;