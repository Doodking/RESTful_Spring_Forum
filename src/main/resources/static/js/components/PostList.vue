<template>
    <v-layout align-space-around justify-start column>
    <post-form :posts="posts" :postAttr="post"/>
    <post-row
            v-for="post in posts"
            :post="post"
            :posts="posts"
            :key="post.id"
            :editPost="editPost"
            :deletePost="deletePost"/>
    </v-layout>
</template>

<script>
    import PostRow from 'components/PostRow.vue'
    import PostForm from 'components/PostForm.vue'
    import postsApi from 'api/posts'
    export default {
          props: ['posts'],
          components: {
              PostRow,
              PostForm
          },
          data(){
               return{post: null}
          },
          computed: {
              sortedPosts(){
                    return this.posts.sort((a,b) => -(a.id - b.id))
              }
          },
          methods: {
            editPost(post){
                this.post = post
            },
            deletePost(post){
                postsApi.remove(post.id).then(result => {
                    if (result.ok){
                        this.posts.splice(this.posts.indexOf(post), 1)
                    }
                })
            }
          }
    }
</script>

<style>

</style>