import React, { Component } from "react";
import "./App.css";
import { Theme as UWPThemeProvider, getTheme } from "react-uwp/Theme";
import Login from "../Components/LoginComponent/Login";
import NavigationView from "react-uwp/NavigationView";
import Button from "react-uwp/Button";
import SplitViewCommand from "react-uwp/SplitViewCommand";
import SplitView from "react-uwp/SplitView";
import Tabs, { Tab } from "react-uwp/Tabs";
import Header from "../Components/Header/Header";
import BorrowView from "../Components/Borrow/BorrowView/BorrowView";
import LendView from "../Components/Lend/LendView/LendView";

export class App extends Component {

    constructor(props){
        super(props);

        this.tab = React.createRef();
    }


    render() {
        return (
            <UWPThemeProvider
                theme={getTheme({
                    themeName: "light", // set custom theme
                    accent: "#0078D7", // set accent color
                    useFluentDesign: true, // sure you want use new fluent design.
                    desktopBackgroundImage:
                        "https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/U4GOIU-/summer-famous-phuket-island-orange-sunset-panorama-4k-thailand_ewajnerhg__F0000.png"
                })}
            >
                <Header />
                <NavigationView
                    navigationTopNodes={[
                        <SplitViewCommand
                            onClick={() => this.onClickNode(0)}
                            icon={"\uE716"}
                            visited={true}
                        />,
                        <SplitViewCommand
                            onClick={() => this.onClickNode(1)}
                            label="Print"
                            icon="PrintLegacy"
                        />
                    ]}
                >
                    <Tabs ref={this.tab} animateMode="in">
                        <Tab title="Borrow">
                            <BorrowView />
                        </Tab>
                        <Tab title="Lend">
                            <LendView />
                        </Tab>
                        <Tab title="Inquiry" />
                    </Tabs>
                </NavigationView>
            </UWPThemeProvider>
        );
    }

    onClickNode(index) {
        this.tab.current.setState({tabFocusIndex : parseInt(index)}) ;
        console.log(this.tab.current.state);
        //this.tab.state = {tabIndex : index};

    }
}

/*

                <Tabs animateMode="in-out" class="tabs">
                    <Tab title="Borrow">
                        <BorrowView />
                    </Tab>
                    <Tab title="Lend">
                        <LendView />
                    </Tab>
                    <Tab title="Inquiry" />
                </Tabs>



            
*/

export default App;
