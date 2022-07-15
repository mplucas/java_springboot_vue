<template>
  <div id="app">
    <menu-header :routes="routes"></menu-header>
    <router-view></router-view>
  </div>
</template>

<script>
import Menu from './components/shared/menu/Menu.vue'
import { routes } from './routes.ts'

export default {
  components: {
    "menu-header": Menu
  },
  data() {
    return {
      routes
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
