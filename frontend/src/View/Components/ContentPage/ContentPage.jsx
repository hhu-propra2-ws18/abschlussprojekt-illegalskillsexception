import React from "react";
import NavigationView from "react-uwp/NavigationView";

import BorrowView from "../Borrow/BorrowView/BorrowView";
import LendView from "../Lend/LendView/LendView";
import TreeView, { TreeItem } from "react-uwp/TreeView";
import Tabs, { Tab } from "react-uwp/Tabs";
import SplitViewCommand from "react-uwp/SplitViewCommand";

export default class ContentPage extends React.Component {
    render() {
        return (
            <NavigationView
                expandedWidth={240}
                focusNavigationNodeIndex={0}
                navigationTopNodes={[<TreeView > 


                </TreeView>]}
                navigationBottomNodes={[
                    <SplitViewCommand
                        onClick={() => this.onClickNode(20)}
                        label="Print"
                        icon="PrintLegacy"
                    />
                ]}
            >
                <Tabs id="content-view" ref={this.tab} animateMode="in">
                    <Tab title="Borrow">
                        <BorrowView />
                    </Tab>
                    <Tab title="Lend">
                        <LendView />
                    </Tab>
                    <Tab title="Inquiry" />
                </Tabs>
            </NavigationView>
        );
    }
}
