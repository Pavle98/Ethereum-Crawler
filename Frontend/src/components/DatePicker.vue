<template>
  <div class="d-flex justify-content-center">
    <div class="col-md-6">
      <div class="mb-3">
        <label for="selectedDate" class="form-label">Select a date:</label>
        <input type="date" class="form-control" id="selectedDate" v-model="selectedDate">
      </div>
      <button class="btn btn-primary" @click="checkBalance">Check balance for selected date</button>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      selectedDate: "",
    };
  },
  methods: {
    async checkBalance() {
      try {
        this.$emit("date-selected", this.selectedDate);
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>
