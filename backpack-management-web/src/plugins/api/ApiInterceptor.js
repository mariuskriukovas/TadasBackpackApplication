import axios from "axios";
import isArray from "lodash-es/isArray";

export default () => {
  const instance = axios.create({
    baseURL: "/api",
    withCredentials: false,
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  });
  instance.getPageableParams = function getPageableParams(options) {
    let sortOrder = null;
    if (options.sortDesc[0] === true) {
      sortOrder = "desc";
    } else if (options.sortDesc[0] === false) {
      sortOrder = "asc";
    }
    const sort = [options.sortBy[0], sortOrder].filter(Boolean).join(",");
    const params = { page: options.page - 1, size: options.itemsPerPage };
    if (sort) {
      params.sort = sort;
    }
    return { params };
  };

  instance.mapToFormData = function (form) {
    const formData = new FormData();
    Object.keys(form).forEach((key) => {
      const val = form[key];
      if (isArray(val)) {
        Object.values(val).forEach((value) =>
          value instanceof Blob
            ? formData.append(key, value)
            : formData.append(key, value)
        );
      } else if (val !== null && val !== undefined) {
        formData.append(key, val);
      }
    });
    return formData;
  };
  return instance;
};
