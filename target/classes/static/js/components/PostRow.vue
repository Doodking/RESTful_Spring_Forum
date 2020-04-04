<template>
    <v-card class="green lighten-2 my-2">
        <v-card-text class="white--text">
            {{ post.text }}
        </v-card-text>
        <media v-if="post.link" :post="post"></media>
        <v-card-actions>
            <v-btn @click="edit" small text rounded class="white--text">Edit</v-btn>
            <v-btn  @click="del" small>
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    import { mapActions } from 'vuex'
    import Media from 'components/media/Media.vue'

    export default {
        components: { Media },
        props: ['post', 'editPost', 'deletePost'],
        methods: {
            ...mapActions(['removePostAction']),
            edit(){
                this.editPost(this.post)
            },
            del(){
                this.removePostAction(this.post)
            }
        }
    }
</script>

<style>

</style>