<template>
  <v-container>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Daiktai </span>
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
        >
          <template v-slot:[`item.isMandatory`]="{ item }">
            <v-chip v-if="item.isMandatory" color="red" dark>Taip</v-chip>
            <v-chip v-else color="amber" dark>Ne</v-chip>
          </template>
        </v-data-table>
      </v-card-text>
    </v-card>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Pridėti daiktą </span>
      </v-card-title>
      <v-card-text>
        <v-row>
          <v-col cols="6" sm="6">
            <v-text-field v-model="form.name" clearable hint="Pavadinimas turi būti unikalūs"
                          label="Pavadinimas"></v-text-field>
          </v-col>
          <v-col cols="6" sm="6">
            <v-select v-model="form.isMandatory" :items="yesNoItems" clearable
                      item-text="name" item-value="value" label="Ar privalomas"></v-select>
          </v-col>
        </v-row>
        <v-row>
          <v-col cols="6" sm="6">
            <v-text-field v-model="form.provideKilometers" clearable label="Kilometrai per vienetą" type="number"></v-text-field>
          </v-col>
          <v-col cols="6" sm="6">
            <v-text-field v-model="form.weight" clearable label="Svoris" type="number"></v-text-field>
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
import ItemApi from '../services/ItemApi.js'

export default {
  name: "Items",
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
        text: "Privaloma pasiimti",
        value: "isMandatory",
        sortable: true,
        align: "start",
      },
      {
        text: "Kilometrai, kuriems užtenka vieneto",
        value: "provideKilometers",
        sortable: false,
        align: "start",
      },
      {
        text: "Svoris",
        value: "weight",
        sortable: false,
        align: "start",
      },
    ],
    form: {
      name: null,
      isMandatory: null,
      provideKilometers: null,
      weight: null,
    },
    yesNoItems: [
      {
        name: 'Taip',
        value: true
      },
      {
        name: 'Ne',
        value: false
      }
    ],
  }),
  watch: {
    options: {
      async handler() {
        await this.getItems()
      },
      deep: true,
    },
  },
  methods: {
    async refresh(){
      await this.getItems()
    },

    async getItems() {
      const data = await ItemApi.getItems(this.options)
      this.items = data?.content ?? []
      this.totalElements = data?.totalElements
    },

    async save() {
      await ItemApi.addItem(this.form).then(() => {
        this.showSuccessAlert()
      }).catch(() => {
        this.showErrorAlert()
      })
    },
  },
};
</script>
