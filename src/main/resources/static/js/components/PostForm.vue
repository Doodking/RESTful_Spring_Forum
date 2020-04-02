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
    import { sendPost } from 'util/ws'

    export default {
        props: ['posts', 'postAttr'],
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
            save(){
                sendPost({id: this.id, text: this.text})
                this.text = ''
                this.id = ''

            }
        }
    }
</script>

<style>

</style>