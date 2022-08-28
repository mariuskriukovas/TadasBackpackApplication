<template>
  <v-container>
    <v-card class="mt-4">
      <v-card-title>
        <span class="text-h5"> Keliautojai </span>
      </v-card-title>
      <v-card-text>
      </v-card-text>
    </v-card>
  </v-container>
</template>

<script>

export default {
  name: "BagView",
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
  },
  methods: {},
};
</script>
