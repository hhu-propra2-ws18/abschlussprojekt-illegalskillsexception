import React from "react";
import Button from "react-uwp/Button";
import TextBox from "react-uwp/TextBox";
import PasswordBox from "react-uwp/PasswordBox";
import Dialog from "react-uwp/Dialog";
import * as PropTypes from "prop-types";

import "./LandingPage.css";
import { registerUser, loginUser as loginUserService} from "../../../Services/Authentication/userApi";


export default class LandingPage extends React.Component {
    static contextTypes = { theme: PropTypes.object };

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
            <div id="greeting-div">
                <p>Welcome to fRENTly the simple Webapp for renting items.</p>
                <div className="login-buttons-div">
                    <Button
                        className="login-button"
                        onClick={() => this.showRegister()}
                    >
                        Register
                    </Button>
                    <Button
                        className="login-button"
                        onClick={() => this.showLogin()}
                    >
                        Login
                    </Button>
                </div>

                <Dialog
                    defaultShow={this.state.register}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ register: false })}
                >
                    <div className="dialog-container">
                        <label>Username:</label>
                        <TextBox defaultValue="user" ref={this.nameRegister} />
                        <label>Email:</label>
                        <TextBox defaultValue="idiot@frently.com" ref={this.emailRegister} />
                        <label>Password:</label>
                        <PasswordBox defaultValue="password" ref={this.passwordRegister} />
                        <div className="dialog-buttons-div">
                            <Button onClick={() => this.hideRegister()}>
                                Cancel
                            </Button>
                            <Button onClick={() => this.registerUser()}>
                                Register
                            </Button>
                        </div>
                    </div>
                </Dialog>
                <Dialog
                    defaultShow={this.state.login}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ login: false })}
                >
                    <div className="dialog-container">
                        <label>Username:</label>
                        <TextBox defaultValue="user" ref={this.nameLogin} />
                        <label>Password:</label>
                        <PasswordBox defaultValue="password" ref={this.passwordLogin} />
                        <div className="dialog-buttons-div">
                            <Button onClick={() => this.hideLogin()}>
                                Cancel
                            </Button>
                            <Button onClick={() => this.loginUser()}>
                                Login
                            </Button>
                        </div>
                    </div>
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

    async loginUser(namePassed, passwordPassed) {
        let nameInner = namePassed
            ? namePassed
            : this.nameLogin.current.getValue();
        let passwordInner = passwordPassed
            ? passwordPassed
            : this.passwordLogin.current.getValue();

        loginUserService(nameInner, passwordInner);
    }

    async registerUser() {
        let nameInner = this.nameRegister.current.getValue();
        let emailInner = this.emailRegister.current.getValue();
        let passwordInner = this.passwordRegister.current.getValue();

        await registerUser(nameInner, emailInner, passwordInner);
        this.loginUser(nameInner, passwordInner);
        this.setState({ register: false });
    }
}
