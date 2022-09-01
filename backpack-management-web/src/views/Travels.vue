<template>
  <v-container>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Kelionės </span>
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
        <span class="text-h5"> Pridėti  kelionę</span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="6" sm="6">
            <v-text-field v-model="form.name" clearable hint="Pavadinimas turi būti unikalūs"
                          label="Pavadinimas"></v-text-field>
          </v-col>
          <v-col cols="6" sm="6">
            <v-text-field v-model="form.distanceKilometers" clearable label="Atstumas" type="number"></v-text-field>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" sm="12">
            <v-select v-model="form.traveler" :items="travelers" clearable hint="Vardai turi būti unikalūs"
                      item-text="name" label="Keliautojas" return-object></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-left" cols="12" sm="12">
            <v-btn color="primary" depressed @click="save">Saugoti</v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import TravelApi from '../services/TravelApi.js'
import TravelersApi from '../services/TravelersApi.js'

export default {
  name: "Travels",
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
        text: "Pavadinimas",
        value: "name",
        sortable: true,
        align: "start",
      },
      {
        text: "Atstumas km.",
        value: "distanceKilometers",
        sortable: true,
        align: "start",
      },
      {
        text: "Keliautojas",
        value: "traveler.name",
        sortable: false,
        align: "start",
      },
    ],
    form: {
      name: null,
      distanceKilometers: null,
      traveler: null,
    },
    travelers: [],
  }),
  watch: {
    options: {
      async handler() {
        await this.getTravels()
      },
      deep: true,
    },
  },
  async mounted() {
    await this.getTravelersForSelection()
  },
  methods: {
    async refresh() {
      await this.getTravels()
      await this.getTravelersForSelection()
    },

    async getTravels() {
      const data = await TravelApi.getTravels(this.options)
      this.items = data?.content ?? []
      this.totalElements = data?.totalElements
    },
    async save() {
      await TravelApi.addTravel(this.form).then(() => {
        this.showSuccessAlert()
      }).catch(() => {
        this.showErrorAlert()
      })
    },
    async getTravelersForSelection() {
      const data = await TravelersApi.getTravelersList()
      this.travelers = data ?? []
    },
  },
};
</script>