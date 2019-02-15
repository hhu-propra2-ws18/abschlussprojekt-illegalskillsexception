import React from "react";

export default class ProcessItem extends React.Component {
    render() {
        return (
            <article>
                <h1>{this.props.data.title}</h1>
                <p>
                    <h2>Status</h2>
                    {this.props.data.status}
                </p>
                <p>
                    <h2>Return Date</h2>
                    {this.props.data.returnDate}
                </p>
            </article>
        );
    }
}