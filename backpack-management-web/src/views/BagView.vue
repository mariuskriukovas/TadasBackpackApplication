<template>
  <v-container>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Kuprinės </span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="6" sm="6">
            <v-select v-model="filter.travelerId" :items="travelers" clearable
                      item-text="name" item-value="id" label="Keliautojas"></v-select>
          </v-col>
          <v-col cols="6" sm="6">
            <v-select v-model="filter.travelId" :items="travels" clearable
                      item-text="name" item-value="id" label="Kelionės"></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="12" sm="12">
            <v-select v-model="filter.itemId" :items="bagItems" clearable
                      item-text="name" item-value="id" label="Daiktai"></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-left" cols="12" sm="12">
            <v-btn color="primary" depressed @click="getBagItems">Ieškoti</v-btn>
          </v-col>
        </v-row>
        <v-row class="mt-2">
          <v-col class="text-left" cols="12" sm="12">
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
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5">Komplektuoti kuprinę</span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="6" sm="6">
            <v-select v-model="form.travelId" :items="travels" clearable
                      item-text="name" item-value="id" label="Kelionė"></v-select>
          </v-col>
          <v-col cols="6" sm="6">
            <v-select v-model="form.itemIds" :items="preselectedItems" clearable item-text="name"
                      item-value="id" label="Daiktai" hint="Vienai kelionei galima viena daiktų kombinacija" multiple></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-left" cols="12" sm="12">
            <v-btn color="primary" depressed @click="saveBag">Formuoti kuprinę !</v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5">Skaičiuoti kuprinės svorį</span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12" sm="12">
            <v-select v-model="weightForm.travelId" :items="travels" clearable
                      item-text="name" item-value="id" label="Kelionė"></v-select>
          </v-col>
        </v-row>
        <v-row v-if="countedWeight">
          <v-col class="text-center justify-center" cols="12" sm="12">
            <v-chip color="blue" dark>{{ countedWeight }} kg.</v-chip>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-left" cols="12" sm="12">
            <v-btn color="primary" depressed @click="countBagWeight">Skaičiuoti</v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5">Išvalyti kuprinės turinį</span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="12" sm="12">
            <v-select v-model="clearTravelBagForm.travelId" :items="travels" clearable
                      item-text="name" item-value="id" label="Kelionė"></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col class="text-left" cols="12" sm="12">
            <v-btn color="primary" depressed @click="clearTravelBag">Išvalyti</v-btn>
          </v-col>
        </v-row>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>
import TravelApi from '../services/TravelApi.js'
import TravelersApi from '../services/TravelersApi.js'
import BagApi from '../services/BagApi.js'
import ItemApi from '../services/ItemApi.js'

export default {
  name: "BagView",
  inject: ['showSuccessAlert', 'showErrorAlert'],
  data: () => ({
    name: "BagView",
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
        text: "Kiekis",
        value: "quantity",
        sortable: true,
        align: "start",
      },
      {
        text: "Daikto pavadinimas",
        value: "item.name",
        sortable: true,
        align: "start",
      },
      {
        text: "Kilometrai, kuriems užtenka vieneto",
        value: "item.provideKilometers",
        sortable: true,
        align: "start",
      },
      {
        text: "Vieneto svoris",
        value: "item.weight",
        sortable: true,
        align: "start",
      },
      {
        text: "Kelionės pavadinimas",
        value: "travel.name",
        sortable: true,
        align: "start",
      },
      {
        text: "Keliautojas",
        value: "travel.traveler.name",
        sortable: true,
        align: "start",
      },
    ],

    filter: {
      travelId: null,
      travelerId: null,
      itemId: null,
    },

    form: {
      travelId: null,
      itemIds: [],
    },

    weightForm: {
      travelId: null,
    },
    countedWeight: null,

    clearTravelBagForm: {
      travelId: null,
    },

    items: [],

    travelers: [],
    travels: [],
    bagItems: [],
    preselectedItems: [],
  }),
  computed: {},
  watch: {
    options: {
      async handler() {
        await this.getBagItems()
      },
      deep: true,
    },
  },
  async mounted() {
    await this.getBagItems()

    await this.getTravelersForSelection()
    await this.getTravelsForSelection()
    await this.getItemsForSelection()
    await this.getPreselectedItemsForSelection()
  },
  methods: {
    async getBagItems() {
      const data = await BagApi.getBagItems(this.filter, this.options)
      this.items = data?.content ?? []
      this.totalElements = data?.totalElements
    },
    async saveBag() {
      await BagApi.createBag(this.form).then(() => {
        this.showSuccessAlert()
      }).catch(() => {
        this.showErrorAlert()
      })
    },
    async countBagWeight() {
      await TravelApi.countTravelBagWeight(this.weightForm?.travelId).then((val) => {
        this.countedWeight = val
        this.showSuccessAlert()
      }).catch(() => {
        this.showErrorAlert()
      })
    },
    async clearTravelBag() {
      await TravelApi.clearTravelBag(this.clearTravelBagForm?.travelId).then((val) => {
        this.countedWeight = val
        this.showSuccessAlert()
      }).catch(() => {
        this.showErrorAlert()
      })
    },
    async getTravelersForSelection() {
      const data = await TravelersApi.getTravelersList()
      this.travelers = data ?? []
    },
    async getTravelsForSelection() {
      const data = await TravelApi.getTravelsList()
      this.travels = data ?? []
    },
    async getItemsForSelection() {
      const data = await ItemApi.getItemList()
      this.bagItems = data ?? []
    },
    async getPreselectedItemsForSelection() {
      const data = await ItemApi.getPreselectedItems()
      this.preselectedItems = data ?? []
      this.form.itemIds = this.preselectedItems.filter(i => i.selected).map(e => e.id)
    },
  },
};
</script>
