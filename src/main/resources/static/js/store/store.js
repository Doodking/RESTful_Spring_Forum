import Vue from 'vue';
import Vuex from 'vuex';
import postsApi from 'api/posts'


Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    icons: [
              'fab fa-facebook',
              'fab fa-twitter',
              'fab fa-google-plus',
              'fab fa-linkedin',
              'fab fa-instagram',
    ],
    posts: frontEndData.posts,
    user: frontEndData.user
  },
  getters: {
        sortedPosts: state => state.posts.sort((a,b) => -(a.id - b.id))
  },
  mutations: {
    addPostMutation (state, post) {
      state.posts = [
        ...state.posts,
        post
      ]
    },
    updatePostMutation (state, post) {
          const updateIndex = state.posts.findIndex(item => item.id === post.id)
          state.posts = [
            ...state.posts.slice(0, updateIndex),
            post,
            ...state.posts.slice(updateIndex + 1)
          ]
    },
    removePostMutation (state, post) {
          const deleteIndex = state.posts.findIndex(item => item.id === post.id)
          if(deleteIndex > -1){
            state.posts = [
                ...state.posts.slice(0, deleteIndex),
                ...state.posts.slice(deleteIndex + 1)
            ]
          }
    }
  },
  actions: {
    async addPostAction({commit, state}, post){
        const result = await postsApi.add(post)
        const data = await result.json()
        const index = state.posts.findIndex(item => item.id === data.id)

        if (index > -1) {
            commit('updatePostMutation', data)
        } else {
            commit('addPostMutation', data)
        }
    },
    async updatePostAction({commit}, post){
        const result = await postsApi.update(post)
        const data = await result.json()
        commit('updatePostMutation', data)
    },
    async removePostAction({commit}, post){
        const result = await postsApi.remove(post.id)

        if (result.ok) {
             commit('removePostMutation', post)
        }
    }
  }
})