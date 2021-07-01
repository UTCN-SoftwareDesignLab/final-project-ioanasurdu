import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allGrades() {
        return HTTP.get(BASE_URL + "/grades", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(user) {
        return HTTP.post(BASE_URL + "/grades", user, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(user) {
        return HTTP.patch(BASE_URL + "/grades", user, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(user) {
        return HTTP.delete(BASE_URL + "/grades/" + user.id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    pdf() {
        return HTTP.patch(BASE_URL + "/export/{type}", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    csv() {
        return HTTP.patch(BASE_URL + "/export/{type}", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    }
};
