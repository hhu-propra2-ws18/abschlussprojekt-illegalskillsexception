import React from "react";
import BorrowItemComponent from "./BorrowItemComponent/BorrowItemComponent";

import {connect} from "react-redux";
import { getAllBorrowItems } from "../../../Services/Borrow/borrowCompleteService";


const mapStateToProps = state => {
    return { items: state.borrowstore };
};

export  class BorrowView extends React.Component {

    async componentDidMount(){
        await getAllBorrowItems();
    }

    render() {
        return (
            <>
                <div id="borrow-grid" className="grid-article-view">
                    {this.props.items.map(dataItem => (
                        <BorrowItemComponent
                            key={dataItem.id}
                            data={dataItem}
                        />
                    ))}
                </div>
            </>
        );
    }
}

let borrowViewExport = connect(
    mapStateToProps,
    null,
    null
)(BorrowView);
export default borrowViewExport;
