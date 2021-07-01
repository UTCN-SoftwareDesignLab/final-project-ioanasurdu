import authHeader, { BASE_URL, HTTP } from "../http";

export default {
    allStudents() {
        return HTTP.get(BASE_URL + "/students", { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    create(user) {
        return HTTP.post(BASE_URL + "/students", user, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    edit(user) {
        return HTTP.patch(BASE_URL + "/students", user, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
    delete(user) {
        return HTTP.delete(BASE_URL + "/students/" + user.id, { headers: authHeader() }).then(
            (response) => {
                return response.data;
            }
        );
    },
};
