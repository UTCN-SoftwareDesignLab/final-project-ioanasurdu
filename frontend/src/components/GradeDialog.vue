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
                    <v-text-field v-model="grade.student" label="Student" />
                    <v-text-field v-model="grade.date" label="Date" />
                    <v-text-field v-model="grade.grade" label="Grade" />
                    <v-select v-model="grade.subject" label="Subject" item-text="subject" :items="Subjects"></v-select>
                </v-form>
                <v-card-actions>
                    <v-btn @click="persist">
                        {{ isNew ? "Create" : "Save" }}
                    </v-btn>
                    <v-btn @click="deleteUser">Delete Grade</v-btn>
                    <v-btn @click="addGrade">Alert student</v-btn>
                </v-card-actions>
            </v-card>
        </template>
    </v-dialog>
</template>

<script>
    import api from "../api";

    export default {
        name: "GradeDialog",
        props: {
            grade: Object,
            opened: Boolean,
        },
        data() {
            return {
                subjects: ["LP", "SD", "IP", "IS", "CN", "FLT"],
            }
        },
        methods: {
            persist() {
                if (this.isNew) {
                    api.grades
                        .create({
                            student: this.grade.student,
                            date: this.grade.date,
                            grade: this.grade.grade,
                            subject: this.grade.subject,
                        })
                        .then(() => this.$emit("refresh"));
                } else {
                    api.grades
                        .edit({
                            id: this.grade.id,
                            student: this.grade.student,
                            date: this.grade.date,
                            grade: this.grade.grade,
                            subject: this.grade.subject,
                        })
                        .then(() => this.$emit("refresh"));
                }
            },
            deleteUser(){
                api.grades.delete({
                    id: this.grade.id,
                }).then(() => this.$emit("refresh"));
            },
            addGrade(){
                api.websocket.graded(this.grade.student, this.grade.date, this.grade.grade, this.grade.subject);
            }
        },
        computed: {
            isNew: function () {
                return !this.grade.id;
            },
        },
    };
</script>

<style scoped></style>
