import axios from "axios";

export async function registerUser(nameInner,emailInner,passwordInner){
    let request = await axios.post("http://localhost:8080/users/sign-up", {
        username: nameInner,
        email: emailInner,
        password: passwordInner,
        bankAccount: emailInner
    });
}