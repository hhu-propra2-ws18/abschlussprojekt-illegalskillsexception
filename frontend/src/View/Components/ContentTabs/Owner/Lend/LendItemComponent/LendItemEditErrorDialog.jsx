import React from "react";

import Button from "react-uwp/Button";

import "./LendItemEditErrorDialog.css";

export default class LendItemEditErrorDialog extends React.Component {
    render() {
        return (
            <article className="error-dialog">
                <h3>Sorry, you can't do that.</h3>
                <div>
                    <div>
                        <span>
                            {this.props.errorMessage}
                        </span>
                    </div>
                    <Button onClick={this.props.closeDialog}>Exit</Button>
                </div>
            </article>
        );
    }
}
