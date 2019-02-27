import React from "react";
import LendItemComponent from "./Lend/LendItemComponent/LendItemComponent";

import { connect } from "react-redux";

import "./OwnerView.css";
import { getAllLendItems } from "../../../../Services/Lend/lendCompleteService";
import { getAllSellItemsOfUser } from "../../../../Services/Sell/sellCompleteService";
import SellItemComponent from "./Sell/SellItemComponent/SellItemComponent";
import OwnerViewHeader from "./OwnerViewHeader/OwnerViewHeader";

const mapStateToProps = state => {
    return { itemsLend: state.lendstore, itemsSell: state.sellstore };
};
export class OwnerView extends React.Component {
    async componentDidMount() {
        await getAllLendItems();
        await getAllSellItemsOfUser();
    }

    render() {
        return (
            <div id="lendview-container">
                <OwnerViewHeader />
                <h2>Up for rent</h2>
                <div id="lend-grid" className="grid-article-view">
                    {this.props.itemsLend.map(dataItem => (
                        <LendItemComponent key={dataItem.id} data={dataItem} />
                    ))}
                </div>
                <h2>Up for sale</h2>
                <div className="grid-article-view">
                    {this.props.itemsSell.map(item => (
                        <SellItemComponent data={item} key={item.id} />
                    ))}
                </div>
            </div>
        );
    }
}

let ownerViewExport = connect(
    mapStateToProps,
    null,
    null
)(OwnerView);
export default ownerViewExport;
