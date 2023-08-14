import Vue from 'vue'
import App from './App.vue'
import * as VueGoogleMaps from 'vue2-google-maps'
import "./assets/global.css"


Vue.config.productionTip = false
let num= "34525Agdgthwprorjghgfkglgoggjpr~AIzaSyB90nncGUhKa-lbLABMROQyUJa5CNNifdsVA*%(%*(%&%%*%*QFEDHGG<>FRLTKIT))"
let hash = num.substring(num.indexOf('~')+1, num.indexOf('V'))
Vue.use(VueGoogleMaps, {
  load: {
    key:hash,
  },
})

new Vue({
  render: h => h(App),
}).$mount('#app')

