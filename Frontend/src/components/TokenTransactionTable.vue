<template>
<div>
  <table>
    <thead>
      <tr>
        <th>Transaction Hash</th>
        <th>Age</th>
        <th>From</th>
        <th>To</th>
        <th>Value</th>
        <th>Token</th>
      </tr>
    </thead>
   <tbody>
  <tr v-for="(transaction, index) in paginatedTransactions" :key="index">
    <td>{{ transaction.hash }}</td>
    <td>{{ transaction.timeStamp }}</td>
    <td>
      <a :href="'http://localhost:8081/address=' + transaction.from">
        {{ transaction.from }}
      </a>
    </td>
    <td>
      <a :href="'http://localhost:8081/address=' + transaction.to">
        {{ transaction.to }}
      </a>
    </td>
    <td>{{ transaction.value }}</td>
    <td>{{ transaction.tokenSymbol }}</td>
  </tr>
</tbody>
  </table>
  <div class="pagination">
      <button :disabled="currentPage === 1" @click="prevPage">&lt;</button>
      <button
        v-for="page in totalPages"
        :key="page"
        @click="setCurrentPage(page)"
        :class="{ active: currentPage === page }"
      >
        {{ page }}
      </button>
      <button :disabled="currentPage === totalPages" @click="nextPage">&gt;</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    transactions: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      currentPage: 1,
      itemsPerPage: 10,
    };
  },
  computed: {
    paginatedTransactions() {
      const startIndex = (this.currentPage - 1) * this.itemsPerPage;
      const endIndex = startIndex + this.itemsPerPage;
      return this.transactions.slice(startIndex, endIndex);
    },
    totalPages() {
      return Math.ceil(this.transactions.length / this.itemsPerPage);
    },
  },
  methods: {
    setCurrentPage(page) {
      this.currentPage = page;
    },
    prevPage() {
      this.currentPage--;
    },
    nextPage() {
      this.currentPage++;
    },
  },
};
</script>

<style>
table {
  width: 75%;
  border-collapse: collapse;
}

th, td {
  padding: 0.5rem;
  text-align: left;
  border: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}

td:first-child {
  white-space: nowrap;
  overflow-x: auto;
}
</style>

<style>
table {
  width: 75%;
  border-collapse: collapse;
}

th, td {
  padding: 0.5rem;
  text-align: left;
  border: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
}

tr:nth-child(even) {
  background-color: #f2f2f2;
}

tr:hover {
  background-color: #ddd;
}

td:first-child {
  white-space: nowrap;
  overflow-x: auto;
}
</style>
