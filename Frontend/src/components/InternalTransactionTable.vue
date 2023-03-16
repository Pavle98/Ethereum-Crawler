<template>
  <div>
    <table>
      <thead>
        <tr>
          <th>Parent Txn Hash</th>
          <th>Block</th>
          <th>Age</th>
          <th>From</th>
          <th>To</th>
          <th>Value</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="transaction in paginatedTransactions" :key="transaction.hash">
          <td>{{ transaction.hash }}</td>
          <td>{{ transaction.blockNumber }}</td>
          <td>{{ transaction.timeStamp }}</td>
          <td>
            <a :href="'http://localhost:8081/address=' + transaction.from">
              {{ transaction.from }}
            </a>
          </td>
          <td>{{ transaction.to }}</td>
          <td>{{ transaction.value }}</td>
        </tr>
      </tbody>
    </table>
    <div class="pagination">
      <button :disabled="currentPage === 1" @click="prevPage">&lt;</button>
      <button
        v-for="page in displayedPages"
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
      displayedPages: [],
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
      this.displayedPages = this.getDisplayedPages();
    },
    prevPage() {
      this.currentPage--;
      this.displayedPages = this.getDisplayedPages();
    },
    nextPage() {
      this.currentPage++;
      this.displayedPages = this.getDisplayedPages();
    },
    getDisplayedPages() {
      const maxDisplayedPages = 10;
      const halfMaxDisplayedPages = Math.floor(maxDisplayedPages / 2);
      let startPage = this.currentPage - halfMaxDisplayedPages;
      if (startPage < 1) {
        startPage = 1;
      }
      let endPage = startPage + maxDisplayedPages - 1;
      if (endPage > this.totalPages) {
        endPage = this.totalPages;
        startPage = endPage - maxDisplayedPages + 1;
        if (startPage < 1) {
          startPage = 1;
        }
      }
      const displayedPages = [];
      for (let i = startPage; i <= endPage; i++) {
        displayedPages.push(i);
      }
      return displayedPages;
    },
  },
  created() {
    this.displayedPages = this.getDisplayedPages();
  },
};
</script>

<style>
table {
  width: 100%;
  border-collapse: collapse;
}

th,
td {
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

.pagination {
  margin-top: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
}

.pagination button {
  background-color: #f2f2f2;
  border: none;
  padding: 0.5rem;
  margin: 0 0.25rem;
  cursor: pointer;
  outline: none;
}

.pagination button.active {
  background-color: #ddd;
}

.pagination button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
</style>
