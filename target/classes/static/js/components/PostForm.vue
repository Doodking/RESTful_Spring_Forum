<template>
    <v-layout row>
        <v-col cols="12" sm="11">
            <v-text-field label="New Post" placeHolder="Write something..." v-model="text" />
        </v-col>
        <v-btn class="mt-6" @click="save">
            Save
        </v-btn>
    </v-layout>
</template>

<script>
    import { mapActions } from 'vuex'

    export default {
        props: ['postAttr'],
        data(){
              return{
                text: '',
                id: ''
              }
        },
        watch: {
            postAttr(newVal, oldVal){
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['updatePostAction', 'addPostAction']),
            save(){
                const post = {
                    id: this.id,
                    text: this.text
                }
                if (this.id) {
                    this.updatePostAction(post)
                } else {
                    this.addPostAction(post)
                }
                this.text = ''
                this.id = ''

            }
        }
    }
</script>

<style>

</style>