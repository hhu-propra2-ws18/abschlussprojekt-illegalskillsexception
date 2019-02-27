import React from "react";
import BorrowItemComponent from "./Borrow/BorrowItemComponent/BorrowItemComponent";

import ProgressBar from "react-uwp/ProgressBar";

import { connect } from "react-redux";
import { getAllBorrowItems } from "../../../../Services/Borrow/borrowCompleteService";

import "./ConsumerView.css";
import Tabs, { Tab } from "react-uwp/Tabs";
import BuyItemComponent from "./Buy/BuyItemComponent/BuyItemComponent";
import { getAllBuyItems } from "../../../../Services/Buy/buyCompleteService";

const mapStateToProps = state => {
    return { itemsBorrow: state.borrowstore, itemsBuy: state.buystore };
};

export class ConsumerView extends React.Component {
    constructor(props) {
        super(props);

        this.state = { isLoading: true };
    }

    async componentDidMount() {
        await getAllBorrowItems();
        await getAllBuyItems();
        this.setState({ isLoading: false });
    }

    render() {
        return (
            <div className="borrow-view">
                {this.state.isLoading ? (
                    <div className="loading-div">
                        <ProgressBar isIndeterminate={true} />
                    </div>
                ) : (
                    <Tabs style={{ display: "block" ,width:"100%"}}>
                        <Tab
                            title="Up for rent"
                            style={{ width: "100%", height: "100%" }}
                        >
                            <div id="borrow-grid" className="grid-article-view">
                                {this.props.itemsBorrow.map(dataItem => (
                                    <BorrowItemComponent
                                        key={dataItem.id}
                                        data={dataItem}
                                    />
                                ))}
                            </div>
                        </Tab>
                        <Tab
                            title="Up for sale"
                            style={{ width: "100%", height: "100%" }}
                        >
                            <div className="grid-article-view">
                                {this.props.itemsBuy.map(item => (
                                    <BuyItemComponent
                                        data={item}
                                        key={item.id}
                                    />
                                ))}
                            </div>
                        </Tab>
                    </Tabs>
                )}
            </div>
        );
    }
}

let borrowViewExport = connect(
    mapStateToProps,
    null,
    null
)(ConsumerView);
export default borrowViewExport;
