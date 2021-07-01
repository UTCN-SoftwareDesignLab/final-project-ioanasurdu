import SockJS from "sockjs-client"
import Stomp from "stomp-client"

export default {
    graded(student, date, grade, subject) {
        if (this.stompClient && this.stompClient.connected) {
            const msg = {
                student: student,
                date: date,
                grade: grade,
                subject: subject,
            };
            this.stompClient.send("/app/checkIn", JSON.stringify(msg), {});
        }
        else{
            console.log("Not connected");
        }
    },
    connect() {
        let connected = false;
        this.socket = new SockJS("http://localhost:8088/gs-guide-websocket");
        this.stompClient = Stomp.over(this.socket);
        this.stompClient.connect(
            {"Access-Control-Allow-Origin": "*"},
            () => {
                connected = true;
            },
            error => {
                console.log(error);
                connected = false;
            }
        );
        return connected;
    },
};