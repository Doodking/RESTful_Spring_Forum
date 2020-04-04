<template>
    <v-container class="mt-6">
        <v-row justify="center" class="mt-6">
            <v-btn
                    class="mt-6"
                    color="primary"
                    dark
                    height="200px"
                    width="200px"
                    @click.stop="dialog = true"
            >
                Click_Here@
            </v-btn>

            <v-dialog
                    v-model="dialog"
                    max-width="290"
            >
                <v-card>
                    <v-card-title class="headline">Authorization?</v-card-title>

                    <v-card-text>
                        Our forum is using the oAuth2 (Google authorization) to register our guests on forum. Click <a href="/login">to register</a> or Exit to exit
                    </v-card-text>

                    <v-card-actions>
                        <v-spacer></v-spacer>

                        <v-btn
                                color="green darken-1"
                                text
                                @click="dialog = false"
                        >
                            Exit
                        </v-btn>

                        <v-btn
                                color="green darken-1"
                                text
                                @click="Auth"
                        >
                            LogIn
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        data () {
          return {
            dialog: false,
          }
        },
        name: 'Auth',
        methods: {
            Auth(){
                    this.$router.push('/login')
                },
        }
    }
</script>

<style>

</style>