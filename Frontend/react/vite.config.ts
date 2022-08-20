import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import VitePluginHtmlEnv from 'vite-plugin-html-env'
import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@apis' : resolve( __dirname , 'apis' ) ,
      '@assets' : resolve( __dirname , 'src/assets' ) ,
      '@components' : resolve( __dirname , 'src/components' ) ,
      '@contexts' : resolve( __dirname , 'src/contexts' ) ,
      '@helper' : resolve( __dirname , 'src/helper' ) ,
      '@hooks' : resolve( __dirname , 'src/hooks' ) ,
      '@pages' : resolve( __dirname , 'src/pages' ) ,
      '@services' : resolve( __dirname , 'src/services' ) ,
      '@utils' : resolve( __dirname , 'src/utils' )
    }
  },
  plugins: [
    react(),
    VitePluginHtmlEnv(),
    // or
    // VitePluginHtmlEnv({
    //  CUSTOM_FIELD
    // })

    // Customizable prefixes and suffixes
    // VitePluginHtmlEnv({
    //   prefix: '<{',
    //   suffix: '}>',
    //   envPrefixes: ['VITE_', 'CUSTOME_PREFIX_']
    // })

    // Enable new compile mode by default
    // 1. add directives => vite-if, vite-else
    // 2. Compatible with `import.meta.env.VITE_APP__****`
    // If there are compatibility issues with the new version, please raise the `issue` or submit a `merge request`, I will deal with it promptly in my personal free time.
    VitePluginHtmlEnv({
      compiler: true
      // compiler: false // old
    })
  ]
})
