import React from "react";
import BorrowItemComponent from "./BorrowItemComponent/BorrowItemComponent";

import ProgressRing from "react-uwp/ProgressRing";
import ProgressBar from "react-uwp/ProgressBar";

import { connect } from "react-redux";
import { getAllBorrowItems } from "../../../../Services/Borrow/borrowCompleteService";

import "./BorrowView.css";

const mapStateToProps = state => {
    return { items: state.borrowstore };
};

export class BorrowView extends React.Component {
    constructor(props) {
        super(props);

        this.state = { isLoading: true };
    }

    async componentDidMount() {
        await getAllBorrowItems();
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
                    <div id="borrow-grid" className="grid-article-view">
                        {this.props.items.map(dataItem => (
                            <BorrowItemComponent
                                key={dataItem.id}
                                data={dataItem}
                            />
                        ))}
                    </div>
                )}
            </div>
        );
    }
}

let borrowViewExport = connect(
    mapStateToProps,
    null,
    null
)(BorrowView);
export default borrowViewExport;
