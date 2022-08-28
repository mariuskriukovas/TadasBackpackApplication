import ApiInterceptor from "@/plugins/api/ApiInterceptor";

const api = ApiInterceptor();

export default {
  async getTravels(pageble) {
    return (await api.get("v1/travels", api.getPageableParams(pageble)))?.data
  },
  async addTravel(data) {
    return (await api.post("v1/travels", data))
  },
};