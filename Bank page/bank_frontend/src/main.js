import { createApp }                                   from 'vue'
import App                                             from './App.vue'
import router                                          from './router'
import { ApolloClient, createHttpLink, InMemoryCache } from '@apollo/client/core'
import { createApolloProvider }                        from '@vue/apollo-option'
import { setContext }                                  from 'apollo-link-context'

const httpLink = createHttpLink({
    uri: 'https://misiontic-2021-apigateway.herokuapp.com/'
});

const authLink = setContext((_, { headers }) => {
    return {
        headers: {
            ...headers,
            "Authorization": localStorage.getItem("tokenAccess") || ""
        }
    }
})

const apolloClient = new ApolloClient({
    link : authLink.concat(httpLink),
    cache: new InMemoryCache()
})

const apolloProvider = new createApolloProvider({
    defaultClient: apolloClient
})


createApp(App).use(router).use(apolloProvider).mount('#app')