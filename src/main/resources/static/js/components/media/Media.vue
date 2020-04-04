<template>
    <v-card class="mb-6">
        <v-flex v-if="type === 'href'" xs12 sm6 offset-sm3>
            <v-img v-if="post.linkCover" :src="post.linkCover" aspect-ratio="2.75"></v-img>
            <v-card-title>
                <div>
                    <h3>
                        <a :href="post.link">{{post.linkTitle || post.link}}</a>
                    </h3>
                    <div v-if="post.linkDescription">{{post.linkDescription}}</div>
                </div>
            </v-card-title>
        </v-flex>
        <v-flex v-if="type === 'image'" xs12 sm6 offset-sm3>
            <a :href="post.link">
                <v-img v-if="post.linkCover" :src="post.linkCover" aspect-ratio="2.75"></v-img>
                {{post.link}}
            </a>
        </v-flex>
        <v-flex v-if="type === 'youtube'" xs12 sm6 offset-sm3>
            <Youtube :src="post.link"></Youtube>
        </v-flex>
    </v-card>
</template>

<script>
    import Youtube from './Youtube.vue'
    export default {
        name: 'Media',
        components: { Youtube },
        props: ['post'],
        data() {
            return {type: 'href'}
        },
        computed: {
            targetSrc(){
                let parts = this.src.split('/')
                return 'https://www.youtube.com/embed/${parts[parts.length - 1]}'
            }
        },
        beforeMount(){
            if (this.post.link.indexOf('youtu') > -1) {
                this.type = 'youtube'
            } else if (this.post.link.match(/\.(jpeg|jpg|gif|png)$/) !== null) {
                this.type = 'image'
            } else {
                this.type = 'href'
            }
        }
    }
</script>

<style>

</style>