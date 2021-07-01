<template>
    <v-card>
        <v-card-title>
            Users
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="createUser">Create User</v-btn>
            <v-btn @click="teachers">Students</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="users"
                :search="search"
                @click:row="editUser"
        ></v-data-table>
        <AdminDialog
                :opened="dialogVisible"
                :user="selectedUser"
                @refresh="refreshList"
        ></AdminDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import AdminDialog from "../components/AdminDialog";
    import router from "@/router";

    export default {
        name: "UserList",
        components: {AdminDialog},
        data() {
            return {
                users: [],
                search: "",
                headers: [
                    {
                        text: "Name",
                        align: "start",
                        sortable: false,
                        value: "name",
                    },
                    { text: "Username", value: "username"},
                    { text: "Email", value: "email" },
                    { text: "Role", value: "role" },
                ],
                dialogVisible: false,
                selectedUser: {},
            };
        },
        methods: {
            editUser(user) {
                this.selectedUser = user;
                this.dialogVisible = true;
            },
            createUser() {
                this.selectedUser = {};
                this.dialogVisible = true;
            },
            teachers(){
                router.push("/students")
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedUser = {};
                this.users = await api.users.allUsers();
            },
        },
        async created() {
            this.users = await api.users.allUsers();
            this.refreshList()
        },
    };
</script>

<style scoped></style>
