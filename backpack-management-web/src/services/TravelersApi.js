import ApiInterceptor from "@/plugins/api/ApiInterceptor";

const api = ApiInterceptor();

export default {
  async getTravelers(pageble) {
    return (await api.get("v1/travelers", api.getPageableParams(pageble)))?.data
  },
  async addTraveler(data) {
    return (await api.post("v1/travelers", api.mapToFormData(data)))
  },
};
