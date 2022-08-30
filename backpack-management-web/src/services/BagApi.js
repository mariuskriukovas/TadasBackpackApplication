import ApiInterceptor from "@/plugins/api/ApiInterceptor";

const api = ApiInterceptor();

export default {
  async getBagItems(filters, pageable) {
    return (await api.get("v1/bags", api.getPageableAndFilterParams(filters, pageable)))?.data
  },
  async createBag(data) {
    return (await api.post("v1/bags", data))
  },
};
