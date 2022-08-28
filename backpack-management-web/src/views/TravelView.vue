<template>
  <v-container>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Keliautojų sąrašas </span>
      </v-card-title>
      <v-card-text>
        <v-data-table
            :headers="headers"
            :items="items"
            :items-per-page="10"
            :footer-props="{
            'items-per-page-options': [10, 20, 50],
          }"
            :options.sync="options"
            :server-items-length="totalElements"
        ></v-data-table>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>

export default {
  name: "TravelView",
  data: () => ({
    headers: [
      {
        text: "Word",
        align: "start",
        sortable: false,
        value: "word",
      },
      {
        text: "Count",
        value: "count",
        sortable: false,
        align: "start",
      },
    ],
    items: [],
    options: {},
    totalElements: null,
    toastSettings: {
      position: "bottomCenter",
    },
  }),
  computed: {},
  watch: {
    options: {
      async handler() {

      },
      deep: true,
    },
  },
  async mounted() {
    await this.getItems()
  },
  methods: {
    async getItems() {
      const data = {}
      this.items = data?.content ?? []
      this.totalElements = data?.totalElements
    },
  },
};
</script>
