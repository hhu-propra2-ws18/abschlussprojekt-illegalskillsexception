import React from "react";
import LendViewHeader from "./LendViewHeader/LendViewHeader";
import LendItemComponent from "./LendItemComponent/LendItemComponent";

import {connect} from "react-redux";

import "./LendView.css";
import { getAllLendItems } from "../../../../Services/Lend/lendCompleteService";

const mapStateToProps = state => {
    return { items: state.lendstore };
};
export class LendView extends React.Component {

    async componentDidMount(){
        await getAllLendItems();
    }

    render() {
        return (
            <div id="lendview-container">
                <LendViewHeader />
                <div id="lend-grid" className="grid-article-view">
                    {this.props.items.map(dataItem => (
                        <LendItemComponent key={dataItem.id} data={dataItem} />
                    ))}
                </div>
            </div>
        );
    }
}



let lendViewExport = connect(
    mapStateToProps,
    null,
    null
)(LendView);
export default lendViewExport;
