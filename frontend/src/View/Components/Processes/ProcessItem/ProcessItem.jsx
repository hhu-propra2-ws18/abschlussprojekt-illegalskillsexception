import React from "react";

export default class ProcessItem extends React.Component {
    render() {
        return (
            <article>
                <h3>{this.props.data.title}</h3>
                <h5>Status</h5>
                <p> {this.props.data.status}</p>
                <h5>Return Date</h5>
                <p> {this.props.data.returnDate}</p>
            </article>
        );
    }
}
