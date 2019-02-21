import React from "react";
import InquiriesViewItem from "./InquiriesViewItem/InquiriesViewItem";

import { connect } from "react-redux";
import { getAllInquiries } from "../../../../Services/Inquiry/inquiryCompleteService";

const mapStateToProps = state => {
    return {
        itemsBorrow: state.inquirystore.borrowList,
        itemsLend: state.inquirystore.lendList
    };
};

export class InquiriesView extends React.Component {
    async componentDidMount() {
        await getAllInquiries();
        console.log(this.props.items);
    }

    render() {
        return (
            <div className="grid-article-view">
                {this.props.items.map(dataItem => (
                    <InquiriesViewItem key={dataItem.id} data={dataItem} />
                ))}
            </div>
        );
    }
}

let inquiryViewEXport = connect(
    mapStateToProps,
    null,
    null
)(InquiriesView);
export default inquiryViewEXport;
