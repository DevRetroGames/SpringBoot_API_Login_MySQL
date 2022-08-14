import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import VitePluginHtmlEnv from 'vite-plugin-html-env'
import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  resolve: {
    alias: {
      '@assets' : resolve( __dirname , 'src/assets' )
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
