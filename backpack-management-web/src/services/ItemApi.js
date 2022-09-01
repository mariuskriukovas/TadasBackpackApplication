import ApiInterceptor from "@/plugins/api/ApiInterceptor";

const api = ApiInterceptor();

export default {
  async getItems(pageble) {
    return (await api.get("v1/items", api.getPageableParams(pageble)))?.data
  },
  async addItem(data) {
    return (await api.post("v1/items", data))
  },
  async getItemList() {
    return (await api.get("v1/items/list"))?.data
  },
};
