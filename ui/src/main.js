import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import "./assets/global.css"
import router from './router'; 

Vue.config.productionTip = false
Vue.use(VueGoogleMaps, {
  load: {
    key: process.env.VUE_APP_API_KEY,
  },
})

new Vue({
  render: h => h(App),router
}).$mount('#app')

