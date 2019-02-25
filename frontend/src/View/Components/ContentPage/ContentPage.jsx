import React from "react";
import NavigationView from "react-uwp/NavigationView";
import SplitViewCommand from "react-uwp/SplitViewCommand";
import Tabs, {Tab} from "react-uwp/Tabs";

import {store} from "../../../Store/reduxInit";
import BorrowView from "../ContentTabs/Borrow/BorrowView";
import LendView from "../ContentTabs/Lend/LendView.jsx";
import TransactionsView from "../ContentTabs/Transactions/TransactionsView";
import InquiriesView from "../ContentTabs/Inquiries/InquiriesView";
import ConflictView from "../ContentTabs/Conflict/ConflictView";
import UserView from "../ContentTabs/User/UserView";

import Toast from "react-uwp/Toast";

import "./ContentPage.css";
import {getAllOverdueTransactions} from "../../../Services/User/userNotificationService.js";

export default class ContentPage extends React.Component {
    constructor(props) {
        super(props);

        this.tabs = React.createRef();
        this.navigation = React.createRef();

        this.state = {showNotifToast: false};
    }

    async componentDidMount() {
        let data = await getAllOverdueTransactions(store.getState().user.token);
        let list = data.data.data;
        console.log(data);
        if (list.length !== 0) {
            this.setState({showNotifToast: true})
        }
    }
    render() {
        return (
            <>
                <Toast
                    defaultShow={this.state.showNotifToast}
                    onToggleShowToast={showNotifToast => this.setState({showNotifToast})}
                    title="You have articles that are due to return."
                    description={"View the transaction tab for more informations."}
                    showCloseIcon
                />

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
                    navigationBottomNodes={[<SplitViewCommand
                        onClick={() => this.switchTab(5)}
                        label="Profile"
                        icon={"Contact"}
                    />]}
                    displayMode="compact"
                    autoResize={false}
                >
                    <Tabs
                        tabTitleStyle={{display: "none"}}
                        ref={this.tabs}
                        id="content-view"
                        animateMode="in"
                        style={{display: "block"}}
                    >
                        <Tab title="Borrow" style={{width: "100%", height: "100%"}}>
                            <BorrowView/>
                        </Tab>
                        <Tab title="Lend" style={{width: "100%", height: "100%"}}>
                            <LendView/>
                        </Tab>
                        <Tab title="Inquiries" style={{width: "100%", height: "100%"}}>
                            <InquiriesView/>
                        </Tab>
                        <Tab style={{width: "100%", height: "100%"}}>
                            <TransactionsView/>
                        </Tab>
                        <Tab style={{width: "100%", height: "100%"}}>
                            <ConflictView/>
                        </Tab>
                        <Tab style={{width: "100%", height: "100%"}}>
                            <UserView/>
                        </Tab>
                    </Tabs>
                </NavigationView>
            </>
        );
    }

    switchTab(index) {
        this.navigation.current.setState({ expanded: false });
        this.tabs.current.setState({ tabFocusIndex: index });
    }
}
