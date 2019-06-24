import Vue from 'vue'
import App from './App.vue'
import router from './router'
import 'bootstrap/dist/css/bootstrap.min.css'

import VModal from 'vue-js-modal'
import moment from 'moment'

Vue.use(VModal)

Vue.config.productionTip = false

Vue.filter('formatDate', function(value) {

  if (value) {

    return moment(String(value)).format('DD/MM/YYYY hh:mm')

  }

});

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
