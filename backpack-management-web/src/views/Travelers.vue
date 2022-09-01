<template>
  <v-container>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Keliautojai </span>
        <v-btn icon color="green" @click="refresh">
          <v-icon>mdi-cached</v-icon>
        </v-btn>
      </v-card-title>
      <v-card-text>
        <v-data-table
            :footer-props="{
            'items-per-page-options': [10, 20, 50],
          }"
            :headers="headers"
            :items="items"
            :items-per-page="10"
            :options.sync="options"
            :server-items-length="totalElements"
        ></v-data-table>
      </v-card-text>
    </v-card>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Pridėti keliautoją </span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12" sm="10">
            <v-text-field v-model="name" clearable hint="Vardai turi būti unikalūs" label="Vardas"></v-text-field>
          </v-col>
          <v-col class="text-center justify-center mt-2" cols="12" sm="2">
            <v-btn color="primary" depressed @click="save">Saugoti</v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import TravelersApi from '../services/TravelersApi.js'

export default {
  name: "Travelers",
  inject: ['showSuccessAlert', 'showErrorAlert'],
  data: () => ({
    items: [],
    options: {},
    totalElements: null,

    headers: [
      {
        text: "ID",
        align: "start",
        sortable: true,
        value: "id",
        width: '100px'
      },
      {
        text: "Vardas",
        value: "name",
        sortable: true,
        align: "start",
      },
    ],
  }),
  watch: {
    options: {
      async handler() {
        await this.getTravelers()
      },
      deep: true,
    },
  },
  async mounted() {},
  methods: {
    async refresh() {
      await this.getTravelers()
    },

    async getTravelers() {
      const data = await TravelersApi.getTravelers(this.options)
      this.items = data?.content ?? []
      this.totalElements = data?.totalElements
    },

    async save() {
      await TravelersApi.addTraveler({name: this.name}).then(() => {
        this.showSuccessAlert()
      }).catch(() => {
        this.showErrorAlert()
      })
    }
  },
};
</script>
