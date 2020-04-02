<template>
    <v-card class="green lighten-2 my-2">
        <v-card-text class="white--text">
            {{ post.text }}
        </v-card-text>
        <v-card-actions>
            <v-btn @click="edit" small text rounded class="white--text">Edit</v-btn>
            <v-btn  @click="del" small>
                <v-icon>delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        props: ['post', 'editPost', 'deletePost', 'posts'],
        methods: {
            edit(){
                this.editPost(this.post)
            },
            del(){
                this.deletePost(this.post)
            }
        }
    }
</script>

<style>

</style>