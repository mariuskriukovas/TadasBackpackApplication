<template>
  <v-app>
    <v-main>
      <v-container fluid>
        <v-card color="basil">
          <v-card-title class="text-center justify-center py-6">
            <span class="text-h5">Tado kelionių aplikacija</span>
            <v-icon right>mdi-dog-side</v-icon>
          </v-card-title>
          <v-tabs
              v-model="tab"
              background-color="transparent"
              color="basil"
              grow
          >
            <v-tab v-for="item in items" :key="item.key">
              {{ item.name }}
              <div>
                <v-icon right>{{ item.icon }}</v-icon>
              </div>
            </v-tab>
          </v-tabs>
          <v-tabs-items v-model="tab">
            <v-tab-item v-for="item in items" :key="item.key">
              <v-card color="basil" flat>
                <component :is="item.component"></component>
              </v-card>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
        <v-alert
            v-model="successAlert"
            dismissible
            type="success"
        > Duomenys išsaugoti
        </v-alert>
        <v-alert
            v-model="errorAlert"
            dismissible
            type="error"
        > Veiksmo atlikti nepavyko
        </v-alert>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Travelers from "@/views/Travelers.vue";
import Items from "@/views/Items.vue";
import Travels from "@/views/Travels.vue";
import Backpack from "@/views/Backpack.vue";


export default {
  name: "App",

  components: {
    Travelers,
    Items,
    Travels,
    Backpack,
  },

  data: () => ({
    tab: null,
    items: [
      {
        key: "travelers",
        name: "Keliautojai",
        icon: 'mdi-walk',
        component: Travelers,
      },
      {
        key: "travels",
        name: "Kelionės",
        icon: 'mdi-map',
        component: Travels,
      },
      {
        key: "items",
        name: "Daiktai",
        icon: 'mdi-hammer-screwdriver',
        component: Items,
      },
      {
        key: "bags",
        name: "Kuprinės",
        icon: 'mdi-bag-checked',
        component: Backpack,
      },
    ],
    successAlert: false,
    errorAlert: false,
  }),
  provide() {
    return {
      showSuccessAlert: this.showSuccessAlert,
      showErrorAlert: this.showErrorAlert,
    }
  },
  methods: {
    async showSuccessAlert() {
      this.successAlert = true
      setTimeout(() => {
        this.successAlert = false
      }, 5000)
    },
    async showErrorAlert() {
      this.errorAlert = true
      setTimeout(() => {
        this.errorAlert = false
      }, 5000)
    },
  },
};
</script>