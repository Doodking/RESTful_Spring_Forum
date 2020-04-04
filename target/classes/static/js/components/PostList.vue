<template>
    <v-container>
        <v-layout align-space-around justify-start column>
            <post-form :postAttr="post"/>
            <post-row
                    v-for="post in sortedPosts"
                    :post="post"
                    :key="post.id"
                    :editPost="editPost"
            />
        </v-layout>
    </v-container>
</template>

<script>
    import PostRow from 'components/PostRow.vue'
    import PostForm from 'components/PostForm.vue'
    import postsApi from 'api/posts'
    import { mapGetters } from 'vuex';


    export default {
          components: {
              PostRow,
              PostForm
          },
          data(){
               return{post: null}
          },
          computed:
              mapGetters([
                  'sortedPosts'
                ]),
          methods: {
            editPost(post){
                this.post = post
            }
          }
    }
</script>

<style>

</style>