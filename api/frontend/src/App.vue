<template>
  <div id="app">
    <h1>Busca de Operadoras</h1>
    <SearchBar />
  </div>
</template>

<script>
import SearchBar from './components/SearchBar.vue';

export default {
  components: {
    SearchBar
  }
};
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  text-align: center;
  margin: 50px auto;
  max-width: 600px;
}
</style>