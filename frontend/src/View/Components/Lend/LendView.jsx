import React from "react";
import LendViewHeader from "./LendViewHeader/LendViewHeader";
import LendItemComponent from "./LendItemComponent/LendItemComponent";
import { connect } from "react-redux";

import "./LendView.css";

const items = [
    {
        title: "Der Gerät 9000",
        dailyRate: "3.50",
        deposit: "500",
        location: "Dönerbude um die Ecke",
        id: 1
    },
    {
        title: "Der Gerät 9000",
        dailyRate: "3.50",
        deposit: "500",
        location: "Dönerbude um die Ecke",
        id: 2
    },
    {
        title: "Der Gerät 9000",
        dailyRate: "3.50",
        deposit: "500",
        location: "Dönerbude um die Ecke",
        id: 3
    }
];
const mapStateToProps = state => {
    return { items: state.lendstore };
};
export class LendView extends React.Component {
    render() {
        return (
            <div id="lendview-container">
                <LendViewHeader />
                <div id="lend-grid" className="grid-article-view">
                    {this.props.items.map(function(item,i) {
                       console.log(item);
                       return <LendItemComponent key={this.props.items[i]} data={item} />
                    }
                    )}
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
