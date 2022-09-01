import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@environments' : resolve( __dirname , 'environments' ) ,
      '@apis' : resolve( __dirname , 'apis' ) ,
      '@assets' : resolve( __dirname , 'src/assets' ) ,
      '@components' : resolve( __dirname , 'src/components' ) ,
      '@contexts' : resolve( __dirname , 'src/contexts' ) ,
      '@helpers' : resolve( __dirname , 'src/helpers' ) ,
      '@hooks' : resolve( __dirname , 'src/hooks' ) ,
      '@pages' : resolve( __dirname , 'src/pages' ) ,
      '@routers' : resolve( __dirname , 'src/routers' ) ,
      '@services' : resolve( __dirname , 'src/services' ) ,
      '@utils' : resolve( __dirname , 'src/utils' )
    }
  },
  plugins: [vue()]
})
