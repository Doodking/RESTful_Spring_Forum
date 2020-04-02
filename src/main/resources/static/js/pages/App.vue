<template>
    <v-app class="main-app">
        <v-app-bar app class="green darken-1 white--text text-center">
            <v-avatar tile size="50" class="mr-3">
                <img
                        src="https://www.pfhub.com/wp-content/uploads/2014/01/silk-road.png"
                        alt="John"
                >
            </v-avatar>
            <v-toolbar-title>
                silk road
                anonymous marketplace
            </v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon>
                <v-icon>mdi-magnify</v-icon>
            </v-btn>
            <span v-if="user">{{user.name}}  </span>
            <v-avatar class="mx-3" v-if="user" size="36">
                <img
                        src= "https://im0-tub-ru.yandex.net/i?id=d6926fd5b028222906e950856cc1c6cc&n=13"
                >
            </v-avatar>
            <v-btn v-if="user" icon href="/logout">
                <v-icon>exit_to_app</v-icon>
            </v-btn>
        </v-app-bar>
        <v-content>
            <v-container v-if="!user">Авторизуйтесь, пожалуйста <a href="/login">Google</a>
            </v-container>
            <v-container v-if="user">
                <posts-list  />
            </v-container>
        </v-content>
        <v-footer class="mt-6" dark padless>
            <v-card
                    flat
                    tile
                    class="green darken-1 white--text text-center"
            >
                <v-card-text>
                    <v-btn
                            v-for="icon in icons"
                            :key="icon"
                            class="mx-4 white--text"
                            icon
                    >
                        <v-icon size="24px">{{ icon }}</v-icon>
                    </v-btn>
                </v-card-text>

                <v-card-text class="white--text pt-0">
                    Silk Road was an online black market and the first modern darknet market, best known as a platform for selling illegal drugs. As part of the dark web, it was operated as a Tor hidden service, such that online users were able to browse it anonymously and securely without potential traffic monitoring. The website was launched in February 2011; development had begun six months prior. Initially there were a limited number of new seller accounts available; new sellers had to purchase an account in an auction. Later, a fixed fee was charged for each new seller account.
                </v-card-text>

                <v-divider></v-divider>

                <v-card-text class="white--text">
                    {{ new Date().getFullYear() }} — <strong>SilkRoadMarketplace</strong>
                </v-card-text>
            </v-card>
        </v-footer>
    </v-app>
</template>

<script>
    import { mapState, mapMutations } from 'vuex';
    import PostsList from 'components/PostList.vue'
    import { addHandler } from 'util/ws'


    export default {
        components: {
            PostsList
        },
        computed: mapState(['user', 'icons']),
        methods: mapMutations(['addPostMutation', 'updatePostMutation', 'removePostMutation']),
        created(){
            addHandler(data => {
                if (data.objectType === 'MESSAGE') {
                    switch (data.eventType) {
                        case 'CREATE':
                            this.addPostMutation(data.body)
                            break
                        case 'UPDATE':
                            this.updatePostMutation(data.body)
                            break
                        case 'REMOVE':
                            this.removePostMutation(data.body)
                            break
                        default:
                            console.error(`Looks like the event type if unknown "${data.eventType}"`)
                    }
                } else {
                    console.error(`Looks like the object type if unknown "${data.objectType}"`)
                }
            })
        }
    }

</script>

<style>
.main-app{
    color: red
}
</style>