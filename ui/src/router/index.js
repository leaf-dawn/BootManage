import Vue from 'vue'
import Router from 'vue-router'
import VueResource from 'vue-resource'
import iView from 'iview'
import 'iView/dist/styles/iview.css'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import Login from '@/components/Login'
import Index from '@/components/Index'
import Reader from '@/components/Reader'
import Manager from '@/components/Manager'
import Register from '@/components/Register'

Vue.use(Router)
Vue.use(VueResource)
Vue.use(iView)
// Vue.use(VueQuillEditor,{default global options})
Vue.use(VueQuillEditor)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/index',
      component: Index
    },
    {
      path: '/reader',
      component: Reader
    },
    {
      path: '/manager',
      component: Manager
    },
    {
      path: '/register',
      component: Register
    }
  ]
})
