import ApiInterceptor from "@/plugins/api/ApiInterceptor";

const api = ApiInterceptor();

export default {
  async getBagItems(filters, pageable) {
    return (await api.get("v1/backpack", api.getPageableAndFilterParams(filters, pageable)))?.data
  },
  async packBackpack(data) {
    return (await api.post("v1/backpack/pack", data))
  },
};
