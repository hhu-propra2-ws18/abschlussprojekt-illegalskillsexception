import React, { Component } from "react";
import "./App.css";
import { Theme as UWPThemeProvider, getTheme } from "react-uwp/Theme";
import Header from "../Components/Header/Header";
import LandingPage from "../Components/LandingPage/LandingPage";
import ContentPage from "../Components/ContentPage/ContentPage.jsx";

import { connect } from "react-redux";

const mapStateToProps = state => {
    return { user: state.user };
};

export class App extends Component {
    constructor(props) {
        super(props);

        this.state = { hasUser: false };
    }

    render() {
        return (
            <>
                <Header />
                <main>
                    <UWPThemeProvider
                        theme={getTheme({
                            themeName: "light", // set custom theme
                            accent: "#0078D7", // set accent color
                            useFluentDesign: true, // sure you want use new fluent design.
                            desktopBackgroundImage:
                                "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/U4GOIU-/summer-famous-phuket-island-orange-sunset-panorama-4k-thailand_ewajnerhg__F0000.png"
                        })}
                    >
                        {this.props.user.isLoggedIn ? (
                            <ContentPage />
                        ) : (
                            <LandingPage />
                        )}
                    </UWPThemeProvider>
                </main>
            </>
        );
    }
}

let appExport = connect(
    mapStateToProps,
    null,
    null
)(App);
export default appExport;
