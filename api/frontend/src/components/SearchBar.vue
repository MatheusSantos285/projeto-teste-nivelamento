<template>
  <div>

    <input
      type="text"
      v-model="query"
      placeholder="Buscar operadoras..."
      @input="fetchResults"
    />

    <ul>
      <li v-for="op in results" :key="op.Registro_ANS">
        <strong>{{ op.Razao_Social }}</strong> - {{ op.CNPJ }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  data() {
    return {
      query: '',       
      results: []      
    };
  },
  methods: {
        async fetchResults() {
      if (!this.query) {
        this.results = [];
        return;
      }

      try {
        const response = await fetch(`http://localhost:5000/search?q=${this.query}`);
        if (!response.ok) {
          throw new Error(`Erro HTTP: ${response.status}`);
        }
        const data = await response.json();
        this.results = data;
      } catch (error) {
        console.error("Erro ao buscar operadoras:", error);
        this.results = [];
      }
    }
  }
};
</script>

<style scoped>
input {
  width: 100%;
  padding: 10px;
  margin-bottom: 20px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  padding: 10px;
  border-bottom: 1px solid #eee;
}

li:last-child {
  border-bottom: none;
}
</style>