import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import "./assets/global.css"

Vue.config.productionTip = false
Vue.use(VueGoogleMaps, {
  load: {
    key: "AIzaSyB9xU0nE2HrqMyIHpgC09AgCq4qt8bANP4",
  },

})

new Vue({
  render: h => h(App),
}).$mount('#app')
