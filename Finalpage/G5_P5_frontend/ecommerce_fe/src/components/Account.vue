<template>
    <div id="historial">
        <div class="container">
            <h2>
                Titular de la Cuenta:
                <span>{{ username }}</span>
            </h2>
            <h2>
                Saldo:
                <span>${{ accountByUsername.balance }} COP</span>
            </h2>
            <h2>
                Último Movimiento:
                <span>
                    {{ accountByUsername.lastChange.substring(0,10) }}
                    {{ accountByUsername.lastChange.substring(11,19) }}
                </span>
            </h2>
        </div>

        <h2>Transacciones</h2>
        <div class="container-table">
            <table>
                <tr>
                    <th>Fecha</th>
                    <th>Hora </th>
                    <th>Origen</th>
                    <th>Destino</th>
                    <th>Valor</th>
                    <th>Nota</th>
                </tr>
                <tr v-for="transaction in transactionByUsername" :key="transaction.id">
                    <td>{{ transaction.date.substring(0,10) }}</td>
                    <td>{{ transaction.date.substring(11,19) }}</td>
                    <td>{{ transaction.usernameOrigin }}</td>
                    <td>{{ transaction.usernameDestiny }}</td>
                    <td>${{ transaction.value }} COP</td>
                    <td>{{ transaction.note }}</td>
                </tr>
            </table>
        </div>
    </div>
</template>


<script>
    import gql from 'graphql-tag';
    export default{
        name: "Account",

        data: function(){
            return {
                username: localStorage.getItem("username") || "none",
                transactionByUsername: [],
                accountByUsername: {
                    balance    : 0,
                    lastChange : ""
                }
            }
        },

        apollo: {
            transactionByUsername: {
                query: gql`
                    query Query($username: String!) {
                      transactionByUsername(username: $username) {
                        id
                        usernameOrigin
                        usernameDestiny
                        value
                        note
                        date
                      }
                    }
                `,
                variables() {
                    return {
                        username: this.username,
                    }
                }
            },

            accountByUsername: {
                query: gql`
                    query Query($username: String!) {
                      accountByUsername(username: $username) {
                        balance
                        lastChange
                      }
                    }
                `,
                variables() {
                    return {
                        username: this.username,
                    }
                }
            }
        },

        created: function(){
            this.$apollo.queries.transactionByUsername.refetch();
            this.$apollo.queries.accountByUsername.refetch();
        }
    }
</script>


<style>
    #historial {
        width: 100%;
        display: flex;
        justify-content: flex-start;
        align-items: center;
        flex-direction: column;
    }

    #historial .container-table{
        width:50%;
        max-height: 250px;
        overflow-y: scroll;
        overflow-x: hidden;
    }

    #historial table {
        width: 100%;
        border-collapse: collapse;
        border: 1px solid rgba(0, 0, 0, 0.3);
    }

    #historial table td,
    #historial table th {
        border: 1px solid #ddd;
        padding: 8px;
    }

    #historial table tr:nth-child(even) {
        background-color: #f2f2f2;
    }

    #historial table tr:hover {
        background-color: #ddd;
    }

    #historial table th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: crimson;
        color: white;
    }

    #historial > h2 {
        color: #283747;
        font-size: 25px;
    }

    #historial .container {
        padding: 30px;
        border: 3px solid rgba(0, 0, 0, 0.3);
        border-radius: 20px;
        margin: 5% 0 1% 0;
    }

    #historial .container h2 {
        font-size: 25px;
        color: #283747;
    }

    #historial .container span {
        color: crimson;
        font-weight: bold;
    }
</style>