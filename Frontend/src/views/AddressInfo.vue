<template>
  <div>
    <h2 class="mb-4">Wallet Information</h2>
<div class="row mb-3">
  <div class="col-md-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">ETH Balance</h5>
        <p class="card-text">{{ this.ethBalance }}</p>
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Value</h5>
        <p class="card-text">${{ this.value }}</p>
      </div>
    </div>
  </div>
</div>
<div class="row mb-3">
  <div class="col-md-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">USDT Balance</h5>
        <p class="card-text">{{ this.usdtBalance }}</p>
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Address</h5>
        <p class="card-text">{{ this.address }}</p>
      </div>
    </div>
  </div>
</div>
 <date-picker @date-selected="onDateSelected" :address="address"/>

 <div v-if="showHistoricalBalanceMessage" class="row mt-3">
  <div class="col">
    <div class="card">
      <div class="card-body">
        <p class="card-text">{{ dateMessage }}</p>
      </div>
    </div>
  </div>
</div>
<div v-if="showHistoricalBalanceMessage" class="row mt-3">
  <div class="col-md-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Historical ETH Balance</h5>
        <p class="card-text">{{ historicalEthBalanceMessage }}</p>
      </div>
    </div>
  </div>
  <div class="col-md-6">
    <div class="card">
      <div class="card-body">
        <h5 class="card-title">Historical USDT Balance</h5>
        <p class="card-text">{{ historicalUSDTBalanceMessage }}</p>
      </div>
    </div>
  </div>
</div>

    <div>
       <label for="starting-block">Starting Block:</label>
    <input id="starting-block" v-model="startingBlock" type="number">
    
    <label for="ending-block" style="margin-left: 25px;" >Ending Block:</label>
    <input id="ending-block" v-model="endingBlock" type="number">
  </div>

<p>Search Transactions </p>
<div class="button-container">
  <button class="btn btn-info"  @click="showNormalTransactions = true; showInternalTransactions = false, showUsdtTransactions = false,showAllTokenTransactions = false; fetchNormalTransactions()">Normal Transactions</button>
  <button class="btn btn-info" style="margin-left: 25px;" @click="showInternalTransactions = true; showNormalTransactions = false, showUsdtTransactions = false,showAllTokenTransactions = false; fetchInternalTransactions()">Internal Transactions</button>
  <button class="btn btn-info" style="margin-left: 25px;" @click="showUsdtTransactions = true; showNormalTransactions = false, showInternalTransactions = false,showAllTokenTransactions = false; fetchUsdtTransactions()">USDT Transactions</button>
  <button class="btn btn-info" style="margin-left: 25px;" @click="showAllTokenTransactions = true; showNormalTransactions = false, showInternalTransactions = false, showUsdtTransactions = false; fetchAllTokenTransactions()">Token Transactions</button>
</div>


    <div class="table-container">
      <normal-transaction-table v-if="showNormalTransactions" :transactions="normalTransactions" />
      <internal-transaction-table v-if="showInternalTransactions" :transactions="internalTransactions" />
      <usdt-transaction-table v-if="showUsdtTransactions" :transactions="usdtTransactions" />
      <token-transaction-table v-if="showAllTokenTransactions" :transactions="allTokenTransactions" />
    </div>

    <table v-if="showInternalTransactions">
      <!-- table headers and rows for internal transactions -->
    </table>
  </div>
</template>

<script>
import axios from "axios";
import DatePicker from "@/components/DatePicker.vue";
import NormalTransactionTable from "@/components/NormalTransactionTable.vue";
import InternalTransactionTable from "@/components/InternalTransactionTable.vue";
import UsdtTransactionTable from '../components/UsdtTransactionTable.vue';
import TokenTransactionTable from '../components/TokenTransactionTable.vue';

export default {
 components: {
    NormalTransactionTable,
    InternalTransactionTable,
    UsdtTransactionTable,
    DatePicker,
    TokenTransactionTable,
  },
  params: {
    balance: {
      type: Number,
      required: true,
    },
    value: {
      type: Number,
      required: true,
    },
    address:{
      type: String,
      required: true,
    }
  },
  data() {
    return {
      showNormalTransactions: false,
      showInternalTransactions: false,
      showUsdtTransactions:false,
      showHistoricalBalanceMessage:false,
      normalTransactions: [],
      internalTransactions: [],
      usdtTransactions:[],
      allTokenTransactions:[],
      ethBalance: 0.0,
      value: 0.0,
      usdtBalance: 0.0,
      selectedDate: "",
      historicalEthBalanceMessage:"",
      historicalUSDTBalanceMessage:"",
      dateMessage:"",
      startingBlock: null,
      endingBlock: null,

    };
  },
    created() {
    this.address = this.$route.params.address;
    this.fetchEthereumData();
    this.fetchUsdtData();
  },

  methods: {
     async fetchNormalTransactions() {
      try {
        const response = await axios.get(`http://localhost:8080/transaction/getnormaltransactions/address=${
this.address}&startblock=${this.startingBlock}&endblock=${this.endingBlock}`);
        this.normalTransactions = response.data;
      } catch (error) {
        console.error(error);

      }
    },
     async fetchEthereumData() {
      try {
        const response = await axios.get(`http://localhost:8080/transaction/getbalance/${this.address}`);
        if(response.data.balance == -1){
            this.$router.push({
          name: "Home",
        });
          alert("Bad format for address or address doesnt exist");
          return;
        }
        this.ethBalance = response.data.balance;
        this.value = response.data.value;
        // Do something with the response data
      } catch (error) {
        console.error(error);
      }
    },
    async fetchUsdtData() {
      try {
      
        const response = await axios.get(`http://localhost:8080/transaction/getusdtbalance/${this.address}`);

        this.usdtBalance = response.data;
        if(this.usdtBalance == -1){
          return;
        }
      } catch (error) {
        console.error(error);
      }
    },
    async fetchInternalTransactions() {
      try {
        const response = await axios.get(`http://localhost:8080/transaction/getinternaltransactions/address=${this.address}
&startblock=${this.startingBlock}&endblock=${this.endingBlock}`);
  
        this.internalTransactions = response.data;
      } catch (error) {
        console.error(error);

      }
    },
    async fetchUsdtTransactions() {
      try {

        const response = await axios.get(`http://localhost:8080/transaction/getusdttransactions/address=${this.address}
&startblock=${this.startingBlock}&endblock=${this.endingBlock}`);
  
        this.usdtTransactions = response.data;
      } catch (error) {
        console.error(error);

      }
    },
    async fetchAllTokenTransactions() {
      try {

        const response = await axios.get(`http://localhost:8080/transaction/getalltokentransactions/address=${this.address}
&startblock=${this.startingBlock}&endblock=${this.endingBlock}`);
  
        this.allTokenTransactions = response.data;
      } catch (error) {
        console.error(error);

      }
    },
    
    async onDateSelected(date) {
      this.selectedDate = Date.parse(date)/1000;
      const response = await axios.get(`http://localhost:8080/transaction/infura/address=${this.address}&timestamp=${this.selectedDate}`);
      const response2 = await axios.get(`http://localhost:8080/transaction/infura/gethistoricalusdtbalance/address=${this.address}&timestamp=${this.selectedDate}`);
      const balanceEth = response.data;
      const balanceUsdt = response2.data;
       if (balanceEth < 0) {
    alert("Cannot choose a date in the future");
    return;
  }
      this.showHistoricalBalanceMessage = true;
      this.dateMessage = `On ${date} balance was:`;
      this.historicalEthBalanceMessage= `ETH: ${balanceEth} `;
      this.historicalUSDTBalanceMessage = `USDT: ${balanceUsdt}`
      
    }
  },

};
</script>

<style>
.button-container, .table-container {
  display: inline-block;
  vertical-align: top;
  margin-right: 1px;
}
.card {
  background-color: #e7f0f7;
  border: none;
  border-radius: 10px;
  box-shadow: 0px 0px 10px #c3d3e8;
}

.card-title {
  color: #2c3e50;
  font-size: 1.2rem;
  font-weight: bold;
}

.card-text {
  color: #34495e;
  font-size: 1.5rem;
  font-weight: bold;
}
.custom-bg {
  background-color: #0066cc;
}
.row {
  margin: 0;
}

.mb-3 {
  margin-bottom: 1.5rem;
}

.h2 {
  color: #2c3e50;
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 2rem;
}
div {
  margin-bottom: 30px;
}
</style>