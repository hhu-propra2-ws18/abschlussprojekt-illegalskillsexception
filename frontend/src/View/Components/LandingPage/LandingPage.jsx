import React from "react";
import Button from "react-uwp/Button";
import TextBox from "react-uwp/TextBox";
import PasswordBox from "react-uwp/PasswordBox";
import Dialog from "react-uwp/Dialog";
import * as PropTypes from "prop-types";

import "./LandingPage.css";
import {
    registerAndLoginUser,
    loginUser
} from "../../../Services/User/authentificationCompleteService";

export default class LandingPage extends React.Component {
    static contextTypes = { theme: PropTypes.object };

    constructor(props) {
        super(props);

        this.state = {
            showRegisterDialog: false,
            showLoginDialog: false,
            showError: false
        };

        this.nameRegister = React.createRef();
        this.emailRegister = React.createRef();
        this.passwordRegister = React.createRef();

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
                {this.state.showRegisterDialog ? (
                    <Dialog
                        defaultShow={this.state.showRegisterDialog}
                        style={{ zIndex: 400 }}
                        onCloseDialog={() =>
                            this.setState({ showRegisterDialog: false })
                        }
                    >
                        <form
                            onSubmit={e => {
                                e.preventDefault();
                                this.registerUser();
                            }}
                        >
                            <div className="dialog-container">
                                <label>Username:</label>
                                <TextBox
                                    required={true}
                                    defaultValue="user"
                                    ref={this.nameRegister}
                                />
                                <label>Email:</label>
                                <TextBox
                                    required={true}
                                    defaultValue="idiot@frently.com"
                                    ref={this.emailRegister}
                                />
                                <label>Password:</label>
                                <PasswordBox
                                    required={true}
                                    defaultValue="password"
                                    ref={this.passwordRegister}
                                />
                                <div className="dialog-buttons-div">
                                    <Button onClick={() => this.hideRegister()}>
                                        Cancel
                                    </Button>
                                    <Button
                                        onClick={() => this.registerUser()}
                                        type="submit"
                                    >
                                        Register
                                    </Button>
                                </div>
                            </div>
                        </form>
                    </Dialog>
                ) : null}
                {this.state.showLoginDialog ? (
                    <Dialog
                        defaultShow={this.state.showLoginDialog}
                        style={{ zIndex: 400 }}
                        onCloseDialog={() =>
                            this.setState({ showLoginDialog: false })
                        }
                    >
                        <form
                            onSubmit={e => {
                                e.preventDefault();
                                this.loginUser();
                            }}
                        >
                            <div className="dialog-container">
                                <label>Username:</label>
                                <TextBox
                                    defaultValue="user"
                                    ref={this.nameLogin}
                                    required={true}
                                />
                                <label>Password:</label>
                                <PasswordBox
                                    defaultValue="password"
                                    ref={this.passwordLogin}
                                    required={true}
                                />
                                <div className="dialog-buttons-div">
                                    <Button onClick={() => this.hideLogin()}>
                                        Cancel
                                    </Button>
                                    <Button type="submit">Login</Button>
                                </div>
                            </div>
                        </form>
                    </Dialog>
                ) : null}
                {this.state.showError ? (
                    <Dialog
                        defaultShow={this.state.showError}
                        style={{ zIndex: 400 }}
                        onCloseDialog={() =>
                            this.setState({ showError: false })
                        }
                    >
                        <article>
                            <h5>Sorry, an error occurred.</h5>
                            <p>{this.state.errorMessage}</p>
                            <Button
                                onClick={() =>
                                    this.setState({ showError: false })
                                }
                            >
                                Close
                            </Button>
                        </article>
                    </Dialog>
                ) : null}
            </div>
        );
    }

    hideRegister() {
        this.setState({ showRegisterDialog: false });
    }

    showRegister() {
        this.setState({ showRegisterDialog: true });
    }

    showLogin() {
        this.setState({ showLoginDialog: true });
    }

    hideLogin() {
        this.setState({ showLoginDialog: false });
    }

    async loginUser(namePassed, passwordPassed) {
        let nameInner = namePassed
            ? namePassed
            : this.nameLogin.current.getValue();
        let passwordInner = passwordPassed
            ? passwordPassed
            : this.passwordLogin.current.getValue();

        let result = await loginUser(nameInner, passwordInner);
        if (result.error) {
            this.setState({
                showError: true,
                errorMessage: "Wrong credentials"
            });
            console.log("exception");
        } else {
            this.setState({ showLoginDialog: false });
        }
    }

    async registerUser() {
        let nameInner = this.nameRegister.current.getValue();
        let emailInner = this.emailRegister.current.getValue();
        let passwordInner = this.passwordRegister.current.getValue();

        let result = await registerAndLoginUser(
            nameInner,
            emailInner,
            passwordInner
        );

        console.log("result", result);
        if (result.data.error) {
            this.setState({
                showError: true,
                errorMessage: result.data.error.errorMessage
            });
        }

        if (result.error) {
            this.setState({

                showError: true,
                errorMessage: result.error.errorMessage

            });
        }
    }
}
