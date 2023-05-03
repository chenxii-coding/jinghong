const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    port: 3000,
    historyApiFallback: true,
    allowedHosts: "all",
    proxy: {
      '^/api': {
        target: 'http://localhost:9001/jinghong/api',
        changeOrigin: true,
        ws: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})
