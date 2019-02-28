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
    }

    render() {
        return (
            <div className="grid-article-view">
                {this.props.itemsBorrow.map(dataItem => (
                    <InquiriesViewItem isLendingInquiry={false} key={dataItem.id} data={dataItem} />
                ))}
                {this.props.itemsLend.map(dataItem => (
                    <InquiriesViewItem  isLendingInquiry={true} key={dataItem.id} data={dataItem} />
                ))}
            </div>
        );
    }
}

let inquiryViewExport = connect(
    mapStateToProps,
    null,
    null
)(InquiriesView);
export default inquiryViewExport;
