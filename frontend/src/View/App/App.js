import React, { Component } from "react";
import "./App.css";
import { Theme as UWPThemeProvider, getTheme } from "react-uwp/Theme";
import Header from "./Header/Header";
import LandingPage from "../Components/LandingPage/LandingPage";
import ContentPage from "../Components/ContentPage/ContentPage.jsx";
import FloatNav from "react-uwp/FloatNav";
import IconButton from "react-uwp/IconButton";

import { connect } from "react-redux";

const mapStateToProps = state => {
    return { user: state.user };
};

export class App extends Component {
    constructor(props) {
        super(props);

        this.state = { hasUser: false, theme: "light" };
    }

    render() {
        return (
            <>
                <Header />
                <main>
                    <UWPThemeProvider
                        theme={getTheme({
                            themeName: this.state.theme, // set custom theme
                            accent: "#0078D7", // set accent color
                            useFluentDesign: true // sure you want use new fluent design.
                        })}
                        id="provider"
                    >
                        {this.props.user.isLoggedIn ? (
                            <ContentPage />
                        ) : (
                            <LandingPage />
                        )}
                        <FloatNav
                            id="float-nav"
                            expandedItems={[
                                {
                                    iconNode: (
                                        <IconButton
                                            hoverStyle={{}}
                                            activeStyle={{}}
                                        >
                                            {this.state.theme === "light"
                                                ? "Brightness"
                                                : "QuietHours"}
                                        </IconButton>
                                    ),
                                    title: "Toogle theme",
                                    onClick: () => this.toggleThemeState()
                                }
                            ]}
                        />
                    </UWPThemeProvider>
                </main>
            </>
        );
    }

    toggleThemeState() {
        console.log("std");
        if (this.state.theme === "light") {
            this.setState({ theme: "dark" });
        } else {
            this.setState({ theme: "light" });
        }
    }
}

let appExport = connect(
    mapStateToProps,
    null,
    null
)(App);
export default appExport;
