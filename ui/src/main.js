import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import "./assets/global.css"

Vue.config.productionTip = false
Vue.use(VueGoogleMaps, {
  load: {
    key: '',
  },

})

new Vue({
  render: h => h(App),
}).$mount('#app')
