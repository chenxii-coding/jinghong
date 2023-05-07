<template>
  <div id="header">
    <Header></Header>
  </div>
  <div id="main">
    <RouterView/>
  </div>
</template>

<script>
import Header from '@/components/Header.vue'

export default {
  components: {
    Header
  }
}
</script>

<style>
#app {
  font-family: sans-serif;
  font-weight: 300;
  font-size: 15px;
  line-height: 10px;
  width: 100%;
  height: 100%;
}

#header {
  margin: 0;
  padding: 0 200px;
  box-sizing: border-box;
  max-width: 1280px;
}

#main {
  height: 100%;
  max-width: 1280px;
  padding: 0 200px;
}
</style>