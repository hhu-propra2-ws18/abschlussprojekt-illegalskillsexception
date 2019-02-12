import React, { Component } from "react";
import Button from "react-uwp/Button";
import ContentDialog from "react-uwp/ContentDialog";

export default class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showDialog1: true
        };
    }

    render() {
        return (
            <>
                <h1>login</h1>
                <Button
                    onClick={() => {
                        this.setState({ showDialog1: true });
                    }}
                >
                    Login
                </Button>
                <ContentDialog
                    defaultShow={this.state.showDialog1}
                    style={{ zIndex: 400 }}
                    onCloseDialog={() => this.setState({ showDialog1: false })}
                    primaryButtonText="Sign in/Sign up"
                    secondaryButtonText="Cancel"
                    contentNode={
                        <>
                            <h1>Enter e-mail address to login or signup</h1>
                            <TextBox background="none" />
                        </>
                    }
                />
            </>
        );
    }

    signIn() {
        
    }

    cancel() {}
}
