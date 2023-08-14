import Vue from "vue";
import App from "./App.vue";
import * as VueGoogleMaps from "vue2-google-maps";
import "./assets/global.css";


Vue.config.productionTip = false
let varS = "fiuiuqlk2hfolvkjeoevkesfewn~AIzaSyB90nncGUhKa-lbLABMROQyUJa5CNNifds)RTtrvcnalfjw[;g]mjvdjsfjjhgh"
Vue.use(VueGoogleMaps, {
  load: {
    key:varS.substring(varS.indexOf('~')+1, varS.indexOf(')'))
  },
});

new Vue({
  render: (h) => h(App),
}).$mount("#app");
