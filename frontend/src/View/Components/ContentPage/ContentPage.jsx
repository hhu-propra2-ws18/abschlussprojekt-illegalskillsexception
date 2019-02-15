import React from "react";
import NavigationView from "react-uwp/NavigationView";

import BorrowView from "../Borrow/BorrowView/BorrowView";
import LendView from "../Lend/LendView/LendView";
import TreeView, { TreeItem } from "react-uwp/TreeView";
import Tabs, { Tab } from "react-uwp/Tabs";
import SplitViewCommand from "react-uwp/SplitViewCommand";
import InqueriesView from "../Inqueries/InqueriesView/InqueriesView";

export default class ContentPage extends React.Component {
    constructor(props) {
        super(props);

        this.tabs = React.createRef();
    }

    render() {
        return (
            <NavigationView
                expandedWidth={240}
                focusNavigationNodeIndex={0}
                navigationTopNodes={[
                    <SplitViewCommand
                        onClick={() => this.switchTab(0)}
                        label="Borrow"
                    />,
                    <SplitViewCommand
                        onClick={() => this.switchTab(1)}
                        label="Lend"
                    />,
                    <SplitViewCommand label="Inquiry" />
                ]}
                navigationBottomNodes={[

                ]}
            >
                <Tabs
                    ref={this.tabs}
                    id="content-view"
                    animateMode="in"
                >
                    <Tab title="Borrow">
                        <BorrowView />
                    </Tab>
                    <Tab title="Lend">
                        <LendView />
                    </Tab>
                    <Tab title="Inqueries" >
                    <InqueriesView />
                    </Tab>
                </Tabs>
            </NavigationView>
        );
    }

    switchTab(index) {
        this.tabs.current.setState({ tabFocusIndex: index });
    }
}
