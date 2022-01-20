<template>
    <div id="transaction" class="transaction">
        <div class="container_transaction">
            <h2>Realizar Transacción:</h2>

            <!-- TODO use combobox here -->
            <form v-on:submit.prevent="processTransaction">
                <input type="text" v-model="createTransaction.usernameDestiny" placeholder="Usuario Destino" />
                <br />
                <input type="number" v-model="createTransaction.value" placeholder="Valor">
                <br />
                <input type="text" v-model="createTransaction.note" placeholder="Nota (opcional)">
                <br />
                <button type="submit">Enviar</button>
            </form>      
        </div>
    </div>
</template>

<script>
    import gql from 'graphql-tag';
    export default {
        name: "Transaction",

        data: function() {
            return {
                createTransaction: {
                    usernameOrigin: localStorage.getItem('username'),
                    usernameDestiny: "",
                    value: 0,
                    note: ""
                }
            }
        },

        methods: {
            processTransaction: async function(){
                if(localStorage.getItem("tokenRefresh") === null || localStorage.getItem("tokenAccess") === null) {
                    this.$emit("logOut");
                    return;
                }

                localStorage.setItem("tokenAccess", "");
                await this.$apollo.mutate(
                    {
                        mutation: gql`
                            mutation Refresh($refresh: Refresh!) {
                              refreshToken(refresh: $refresh) {
                                access
                              }
                            }
                        `,
                        variables: {
                            refresh: {
                                refresh: localStorage.getItem("tokenRefresh"),
                            }
                        }
                    }
                )
                .then((result) => {
                    localStorage.setItem("tokenAccess", result.data.refreshToken.access);
                })
                .catch((error) => {
                    console.log(error);
                    this.$emit("logOut");
                    return;
                });

                await this.$apollo.mutate(
                    {
                        mutation: gql`
                            mutation Transaction($transaction: TransactionInput!) {
                              createTransaction(transaction: $transaction) {
                                id
                                usernameOrigin
                                usernameDestiny
                                value
                                note
                                date
                              }
                            }
                        `,
                        variables: {
                            transaction: this.createTransaction,
                        }
                    }
                )
                .then((result) => {
                    console.log(result);
                    // TODO add transaction response to alert 
                    alert("Transacción realizada de manera correcta, revise historial.");
                })
                .catch((error) => {
                    console.log(error);
                    // TODO check error body
                    alert("Saldo Insuficiente o Destino Incorrecto");
                });
            }            
        }
    }
</script>

<style>
    .transaction {
        margin: 0;
        padding: 0%;
        height: 100%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .container_transaction {
        border: 3px solid #283747;
        border-radius: 10px;
        width: 25%;
        height: 60%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .transaction h2 {
        color: #283747;
    }

    .transaction form {
            width: 50%;
    }

    .transaction input {
        height: 40px;
        width: 100%;
        box-sizing: border-box;
        padding: 10px 20px;
        margin: 5px 0;
        border: 1px solid #283747;
    }

    .transaction button {
        width: 100%;
        height: 40px;
        color: #e5e7e9;
        background: #283747;
        border: 1px solid #e5e7e9;
        border-radius: 5px;
        padding: 10px 25px;
        margin: 5px 0;
    }

    .transaction button:hover {
        color: #e5e7e9;
        background: crimson;
        border: 1px solid #283747;
    }
</style>
