<template>
  <div id="main-body">
    <menu-header :routes="routes"></menu-header>
    <router-view></router-view>
  </div>
</template>

<script>
import Menu from './components/shared/menu/Menu.vue'
import { routes } from './routes.js'

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
#main-body {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
  padding: 30px;
}

.fixed-width {
  width: 250px;
}

.centralizado {
    text-align: center;
}
</style>
