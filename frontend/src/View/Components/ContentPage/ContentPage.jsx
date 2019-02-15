import React from "react";
import NavigationView from "react-uwp/NavigationView";

import BorrowView from "../Borrow/BorrowView";
import LendView from "../Lend/LendView.jsx";
import TreeView, { TreeItem } from "react-uwp/TreeView";
import Tabs, { Tab } from "react-uwp/Tabs";
import SplitViewCommand from "react-uwp/SplitViewCommand";
import InquiriesView from "../Inquiries/InquiriesView/InquiriesView";

import "./ContentPage.css";
import ProcessesView from "../Processes/ProcessesView";

export default class ContentPage extends React.Component {
    constructor(props) {
        super(props);

        this.tabs = React.createRef();
        this.navigation = React.createRef();
    }

    render() {
        return (
            <NavigationView
                expandedWidth={240}
                focusNavigationNodeIndex={0}
                ref={this.navigation}
                navigationTopNodes={[
                    <SplitViewCommand
                        onClick={() => this.switchTab(0)}
                        label="Borrow"
                        icon={"\uECCD"}
                    />,
                    <SplitViewCommand
                        onClick={() => this.switchTab(1)}
                        label="Lend"
                        icon={"\uF0AD"}
                    />,
                    <SplitViewCommand
                        onClick={() => this.switchTab(2)}
                        label="Inquiry"
                        icon={"\uE9D5"}
                    />,
                    <SplitViewCommand
                        onClick={() => this.switchTab(3)}
                        label="Processes"
                        icon={"\uE9D5"}
                    />
                ]}
                navigationBottomNodes={[]}
                displayMode="compact"
                autoResize={false}
            >
                <Tabs
                    tabTitleStyle={{ display: "none" }}
                    ref={this.tabs}
                    id="content-view"
                    animateMode="in"
                    style={{ display: "block" }}
                >
                    <Tab title="Borrow" style={{ width: "100%" }}>
                        <BorrowView />
                    </Tab>
                    <Tab title="Lend" style={{ width: "100%" }}>
                        <LendView />
                    </Tab>
                    <Tab title="Inquiries" style={{ width: "100%" }}>
                        <InquiriesView />
                    </Tab>
                    <Tab>
                        <ProcessesView />
                    </Tab>
                </Tabs>
            </NavigationView>
        );
    }

    switchTab(index) {
        this.navigation.current.setState({ expanded: false });
        this.tabs.current.setState({ tabFocusIndex: index });
    }
}
