import axios from "axios";
import qs from 'qs'

export default () => {
    const instance = axios.create({
        baseURL: "/api", withCredentials: false, headers: {
            Accept: "application/json", "Content-Type": "application/json",
        },
    });
    instance.getPageableParams = function getPageableParams(pageable) {
        let sortOrder = null;
        if (pageable.sortDesc[0] === true) {
            sortOrder = "desc";
        } else if (pageable.sortDesc[0] === false) {
            sortOrder = "asc";
        }
        const sort = [pageable.sortBy[0], sortOrder].filter(Boolean).join(",");
        const params = {page: pageable.page - 1, size: pageable.itemsPerPage};
        if (sort) {
            params.sort = sort;
        }
        return {params};
    };

    instance.getPageableAndFilterParams = function getPageableAndFilterParams(filters, pageable) {
        const params = this.getPageableParams(pageable)
        return {
            params: Object.assign(params.params, filters), paramsSerializer: (p) => {
                if (p === null) {
                    return null
                }
                return qs.stringify(filterNonNull(p))
            },
        };
    };

    function filterNonNull(obj) {
        // eslint-disable-next-line no-unused-vars
        return Object.fromEntries(Object.entries(obj).filter(([k, v]) => v))
    }

    return instance;
};
