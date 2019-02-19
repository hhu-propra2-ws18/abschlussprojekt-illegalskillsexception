import React from "react";
import NavigationView from "react-uwp/NavigationView";

import BorrowView from "../ContentTabs/Borrow/BorrowView";
import LendView from "../ContentTabs/Lend/LendView.jsx";
import Tabs, { Tab } from "react-uwp/Tabs";
import SplitViewCommand from "react-uwp/SplitViewCommand";
import InquiriesView from "../ContentTabs/Inquiries/InquiriesView";

import "./ContentPage.css";
import TransactionsView from "../ContentTabs/Transactions/TransactionsView";

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
                        icon={"\uE73E"}
                    />,
                    <SplitViewCommand
                        onClick={() => this.switchTab(3)}
                        label="Processes"
                        icon={"\uE9F5"}
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
                    <Tab title="Borrow" style={{ width: "100%",height:"100%" }}>
                        <BorrowView />
                    </Tab>
                    <Tab title="Lend" style={{ width: "100%",height:"100%" }}>
                        <LendView />
                    </Tab>
                    <Tab title="Inquiries" style={{ width: "100%",height:"100%" }}>
                        <InquiriesView />
                    </Tab>
                    <Tab  style={{ width: "100%",height:"100%" }}>
                        <TransactionsView />
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
