import Vue from 'vue'
import VueRouter from 'vue-router'
import PostsList from 'components/PostList.vue'
import Profile from 'pages/Profile.vue'
import Auth from 'pages/Auth.vue'

Vue.use(VueRouter)

const routes = [
  { path: '/', component: PostsList },
  { path: '/auth', component: Auth },
  { path: '/profile', component: Profile },
  { path: '*', component: PostsList },
]

export default new VueRouter({
  mode: 'history',
  routes
})