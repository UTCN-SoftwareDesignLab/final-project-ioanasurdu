<template>
    <v-card>
        <v-card-title>
            Grades
            <v-spacer></v-spacer>
            <v-text-field
                    v-model="search"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
            ></v-text-field>
            <v-btn @click="createGrade">Add Grade</v-btn>
            <v-btn @click="students">Students</v-btn>
            <v-btn @click="pdf">PDF</v-btn>
            <v-btn @click="csv">CSV</v-btn>
        </v-card-title>
        <v-data-table
                :headers="headers"
                :items="grades"
                :search="search"
                @click:row="editGrade"
        ></v-data-table>
        <GradeDialog
                :opened="dialogVisible"
                :grade="selectedUser"
                :subjects = "subjects"
                @refresh="refreshList"
        ></GradeDialog>
    </v-card>
</template>

<script>
    import api from "../api";
    import router from "@/router";
    import GradeDialog from "@/components/GradeDialog";

    export default {
        name: "GradeList",
        components: {GradeDialog},
        data() {
            return {
                grades: [],
                subjects: [],
                search: "",
                headers: [
                    {
                        text: "Date",
                        align: "start",
                        sortable: false,
                        value: "date",
                    },
                    { text: "Grade", value: "grade"},
                    { text: "Subject", value: "subject"}
                ],
                dialogVisible: false,
                selectedUser: {'subject':{}},
            };
        },
        methods: {
            editGrade(grade) {
                this.selectedUser = grade;
                this.dialogVisible = true;
            },
            createGrade() {
                this.dialogVisible = {'subject':{}};
            },
            students(){
                router.push("/students")
            },
            pdf(){
                this.students.pdf()
            },
            csv(){
                this.students.csv()
            },
            async refreshList() {
                this.dialogVisible = false;
                this.selectedUser = {};
                this.grades = await api.grades.allGrades();
            },
        },
        async created() {
            this.grades = await api.grades.allGrades();
            this.subjects = await api.subjects.allSubjects();
            this.refreshList()
        },
    };
</script>

<style scoped></style>
