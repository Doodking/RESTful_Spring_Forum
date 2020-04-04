<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex row>
                <v-card>
                    <v-img
                      height="620px"
                      width="300px"
                      src="https://palitra62.art/wa-data/public/shop/products/79/38/93879/images/2717/2717.750x0.jpg"
                    >

                    </v-img>
                </v-card>
                <v-card
                        class="mx-auto"
                        max-width="400"
                        height="620"
                >
                    <v-img
                            class="white--text align-end mx-auto"
                            width="400"
                            height="400px"
                            :src=user.userpic
                    >
                    </v-img>

                    <v-card-subtitle class="font-weight-black">PROFILE</v-card-subtitle>

                    <v-card-text class="text--primary">
                        <div>NAME: {{user.name}}</div>
                        <div v-if="user.sex != null">SEX: {{user.sex}}</div>
                        <div v-else="user.sex != null">SEX: UNKNOWN</div>
                        <div>EMAIL: {{user.email}}</div>
                        <div>LANGUAGE: {{user.locale}}</div>
                    </v-card-text>

                    <v-card-actions class="mb-6">
                        <v-btn
                                color="green"
                                text
                        >
                            SUBSCRIBERS
                        </v-btn>

                        <v-btn
                                color="green"
                                text
                        >
                            SUBSCRIPTIONS
                        </v-btn>
                    </v-card-actions>
                </v-card>
                <v-card>
                    <v-img
                            height="620px"
                            width="300px"
                            src="https://palitra62.art/wa-data/public/shop/products/79/38/93879/images/2717/2717.750x0.jpg"
                    >

                    </v-img>
                </v-card>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import { mapState } from 'vuex'
    export default {
        name: 'Profile',
        computed: mapState(['user']),
    }
</script>

<style>
    .img {
        height: 300px;
        width: 300px;
    }
</style>