import Vue from 'vue'
import 'api/res'
import App from 'pages/App.vue'
import { connect } from './util/ws'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

if (frontEndData.user) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    el: '#app',
    vuetify: new Vuetify(),
    render: a => a(App)
})

