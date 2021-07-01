<template>
    <v-dialog
            transition="dialog-bottom-transition"
            max-width="600"
            :value="opened"
    >
        <template>
            <v-card>
                <v-toolbar color="primary" dark>
                    {{ isNew ? "Create user" : "Edit user" }}
                </v-toolbar>
                <v-form>
                    <v-text-field v-model="student.name" label="Name" />
                    <v-text-field v-model="student.email" label="Email" />
                    <v-text-field  v-model="student.cnp" label="CNP" />
                    <v-text-field  v-model="student.address" label="Address" />
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteUser">Delete Student</v-btn>
                    <v-btn @click="addGrade">Grades</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";
    import router from "@/router";

    export default {
        name: "StudentDialog",
        props: {
            student: Object,
            opened: Boolean,
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.students
                        .create({
                            name: this.student.name,
                            email: this.student.email,
                            cnp: this.student.cnp,
                            address: this.student.address,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.students
                        .edit({
                            id: this.student.id,
                            name: this.student.name,
                            email: this.student.email,
                            cnp: this.student.cnp,
                            address: this.student.address,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            deleteUser(){
                api.students.delete({
                    id: this.student.id,
                }).then(() => this.$emit("refresh"));
            },
            addGrade(){
                router.push({ path: `/grades/${this.student.id}` });
            },
        },
        computed: {
            isNew: function () {
                return !this.student.id;
            },
        },
    };
</script>

<style scoped></style>
