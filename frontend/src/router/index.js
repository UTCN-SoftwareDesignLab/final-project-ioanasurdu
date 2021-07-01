import Vue from "vue";
import VueRouter from "vue-router";
import Login from "../views/Login";
import UserList from "../views/UserList.vue";
import StudentList from "../views/StudentList.vue";
import GradeList from "../views/GradeList.vue";
import { auth as store } from "../store/auth.module";

Vue.use(VueRouter);

const routes = [
    {
        path: "/",
        name: "Login",
        component: Login,
    },
    {
        path: "/users",
        name: "Users",
        component: UserList,
        beforeEnter: (to, from, next) => {
            if (store.getters.isAdmin) {
                next();
            } else {
                next({name: "Students"});
            }
        },
    },
    {
        path: "/students",
        name: "Students",
        component: StudentList,
        beforeEnter: (to, from, next) => {
            if (store.state.status.loggedIn) {
                next();
            } else {
                next({ name: "Home" });
            }
        },
    },
    /*{
        path: "/grades",
        name: "Grades",
        component: GradeList,
        beforeEnter: (to, from, next) => {
            if (store.state.status.loggedIn) {
                next();
            } else {
                next({ name: "Home" });
            }
        },
    },*/
    {
        path: "/grades/:id(\\d+)?",
        name: "Grades",
        component: GradeList,
        beforeEnter: (to, from, next) => {
            if (store.state.status.loggedIn) {
                next();
            } else {
                next({ name: "Home" });
            }
        },
    },
    {
        path: "/about",
        name: "About",
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
            import(/* webpackChunkName: "about" */ "../views/About.vue"),
    },
];

const router = new VueRouter({
    routes,
});

export default router;
