<template>
  <div class="home">
    <h2>Enter wallet address:</h2>
    <form @submit.prevent="handleSubmit" class="mt-3">
      <div class="row">
        <div class="col-9 mx-auto">
          <div class="input-group mb-3">
            <input type="text" class="form-control" v-model="address" placeholder="Wallet address">
            <button type="submit" class="btn btn-primary">Submit</button>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
import axios from "axios";
export default {
  
  name: 'Home',
  data() {
    return {
      address: '',
    };
  },
  methods: {
    async handleSubmit() {
      try {
      //  const response = await axios.get(`http://localhost:8080/transaction/getbalance/${this.address}`);
       // const balance = response.data.balance;
       // const value = response.data.value;
       this.$router.push({
          name: "AddressInfo",
          params: {
         //   balance: balance,
          //  value: value,
            address: this.address,
          },
        });
        // Do something with the response data (e.g. navigate to a new page)
      } catch (error) {
        console.error(error);
      }
    },
  },
};
</script>
<style >
body {
  background-color: #ececec; /* replace with desired background color */
}
</style>