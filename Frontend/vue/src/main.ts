import { createApp } from 'vue'

import { createVuestic } from 'vuestic-ui'
import 'vuestic-ui/css'

import router from '@routers/index'
import App from './App.vue'

const app = createApp( App )
app.use( createVuestic() )
app.use( router )
app.mount( '#app' )
