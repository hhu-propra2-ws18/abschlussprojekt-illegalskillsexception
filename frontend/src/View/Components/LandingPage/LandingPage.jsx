import React from "react";
import { Theme as UWPThemeProvider, getTheme } from "react-uwp/Theme";
import Button from "react-uwp/Button";
import TextBox from "react-uwp/TextBox";
import Dialog from "react-uwp/Dialog";
import axios from "axios";
import { store } from "../../../Store/reduxInit";
import { getLoginUserAction } from "../../../Store/UserStore/UserActions";

export default class LandingPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = { register: false, login: false };

        this.nameRegister = React.createRef();
        this.emailRegister = React.createRef();
        this.passwordRegister = React.createRef();
        this.bankRegister = React.createRef();

        this.nameLogin = React.createRef();
        this.passwordLogin = React.createRef();
    }

    render() {
        return (
            <div>
                <p>Hello user</p>
                <Button onClick={() => this.showRegister()}>Register</Button>
                <Button onClick={() => this.showLogin()}>Login</Button>
                <Dialog
                    defaultShow={this.state.register}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ register: false })}
                >
                    <label>Username:</label>
                    <TextBox ref={this.nameRegister} />
                    <label>Email:</label>
                    <TextBox ref={this.emailRegister} />
                    <label>Password:</label>
                    <TextBox ref={this.passwordRegister} />

                    <Button onClick={() => this.hideRegister()}>Cancel</Button>
                    <Button onClick={() => this.registerUser()}>
                        Register
                    </Button>
                </Dialog>
                <Dialog
                    defaultShow={this.state.login}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ login: false })}
                >
                    <label>Username:</label>
                    <TextBox ref={this.nameLogin} />
                    <label>Password:</label>
                    <TextBox ref={this.passwordLogin} />
                    <Button onClick={() => this.hideLogin()}>Cancel</Button>
                    <Button onClick={() => this.loginUser()}>Login</Button>
                </Dialog>
            </div>
        );
    }

    hideRegister() {
        this.setState({ register: false });
    }

    showRegister() {
        this.setState({ register: true });
    }

    showLogin() {
        this.setState({ login: true });
    }

    hideLogin() {
        this.setState({ login: false });
    }

     loginUser(namePassed, passwordPassed) {
        this.hideLogin();
        let loginaction = getLoginUserAction("some-token");
        store.dispatch(loginaction);
        
        /*
        let nameInner = namePassed
            ? namePassed
            : this.nameLogin.current.getValue();
        let passwordInner = passwordPassed
            ? passwordPassed
            : this.passwordLogin.current.getValue();

        
        let user = await axios.post(
            "http://localhost:8080/login",
            {
                username: nameInner,
                password: passwordInner
            },
            {
                
                data: {
                    username: nameInner,
                    password: passwordInner
                }
            }
        );
        
        /*
        var xhr = new XMLHttpRequest();
        var url = "http://localhost:8080/login";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var json = JSON.parse(xhr.responseText);
                console.log(json.email + ", " + json.password);
            }
        };
        var data = JSON.stringify({
            username: nameInner,
            password: passwordInner
        });
        xhr.send(data);

        xhr.onreadystatechange = (data)=> {
            console.log(data);
            console.log(xhr);
            console.log(xhr.getAllResponseHeaders());
            console.log(xhr.getResponseHeader());
            console.log(xhr.response);
        }
        */
        //console.log(user.Authorization);
        //console.log(user);
        


    }

    async registerUser() {
        let nameInner = this.nameRegister.current.getValue();
        let emailInner = this.emailRegister.current.getValue();
        let passwordInner = this.passwordRegister.current.getValue();

        let request = await axios.post("http://localhost:8080/users/sign-up", {
            username: nameInner,
            email: emailInner,
            password: passwordInner,
            bankAccount: emailInner
        });
        try {
            /*
            const Http = new XMLHttpRequest();
            Http.open("POST", "http://localhost:8080/login");
            Http.send({
                username: nameInner,
                password: passwordInner
            });

            Http.onreadystatechange = function() {
                console.log(Http.responseText);
            };
            */
        } catch (exc) {}
        /*
        if (request.status === 200) {

            const api = axios.create({
                baseURL:"http://localhost:8080/login",
                headers:{
                    
                }
            });


        }
        */
        this.loginUser(nameInner, passwordInner);
        this.setState({ register: false });
    }
}
