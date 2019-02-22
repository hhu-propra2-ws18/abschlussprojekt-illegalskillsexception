import React from "react";

import "./BorrowItemErrorDialog.css";

export default class BorrowItemAcceptanceDialog extends React.Component {
    render() {
        return (
            <article className="error-dialog">
                <h3>Sorry, an error occurred.</h3>
                <div>
                    <div>
                        <span>
                            Error type: {this.props.type}
                        </span>
                        <span>
                            Error message: {this.props.message}
                        </span>
                    </div>
                </div>
            </article>
        );
    }
}
