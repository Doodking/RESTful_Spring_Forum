import Vue from 'vue'

const posts = Vue.resource('/posts{/id}')

export default{
    add: post => posts.save({}, post),
    update: post => posts.update({id: post.id}, post),
    remove: id => posts.remove({id: id})
}