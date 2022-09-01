import ApiInterceptor from "@/plugins/api/ApiInterceptor";

const api = ApiInterceptor();

export default {
  async getTravels(pageble) {
    return (await api.get("v1/travels", api.getPageableParams(pageble)))?.data
  },
  async addTravel(data) {
    return (await api.post("v1/travels", data))
  },
  async getTravelsList() {
    return (await api.get("v1/travels/list"))?.data
  },
  async countTravelBackpackWeight(id) {
    return (await api.get(`v1/travels/${id}/weight`))?.data
  },
  async clearTravelBackpack(id) {
    return (await api.delete(`v1/travels/${id}/backpackItems`))?.data
  },
};
