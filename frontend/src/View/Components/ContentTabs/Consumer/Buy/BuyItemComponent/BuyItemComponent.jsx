import React, { Component } from "react";
import Dialog from "react-uwp/Dialog";
import { buyItem } from "../../../../../../Services/Buy/buyCompleteService";
import Button from "react-uwp/Button";

import "./BuyItemComponent.css"

export default class BuyItemComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showBuyDialog: false,
            showErrorDialog: false,
            error: { errorMessage: "" }
        };
    }

    hideBuyDialog() {
        this.setState({ showBuyDialog: false });
    }

    hideErrorDialog() {
        this.setState({ showErrorDialog: false });
    }

    async processBuying() {
        let result = await buyItem(this.props.data.id);
        if (result.data.error) {
            this.setState({
                showErrorDialog: true,
                showBuyDialog: false,
                error: result.data.error
            });
        }
    }

    render() {
        return (
            <article className="buy-article-container">
                <div>
                <h3>{this.props.data.title}</h3>
                    <h5>Description:</h5>
                    <p>{this.props.data.description}</p>
                    <h5>Price:</h5>
                    <p>{this.props.data.price}</p>
                    <h5>Location:</h5>
                    <p>{this.props.data.location}</p>
                    <Button
                        className="bottom-button"
                        onClick={() => this.setState({ showBuyDialog: true })}
                    >
                        Buy item
                    </Button>
                    {this.state.showBuyDialog ? (
                        <Dialog
                            defaultShow={this.state.showBuyDialog}
                            onCloseDialog={() => this.hideBuyDialog()}
                        >
                            <article>
                                <p>
                                    Are you sure you want to buy this item for{" "}
                                    {this.props.data.price}€ ?
                                </p>
                                <div className="dialog-buttons">
                                    <Button
                                        onClick={() => {
                                            this.processBuying();
                                        }}
                                    >
                                        Yes
                                    </Button>
                                    <Button
                                        onClick={() => this.hideBuyDialog()}
                                    >
                                        No
                                    </Button>
                                </div>
                            </article>
                        </Dialog>
                    ) : null}
                    {this.state.showErrorDialog ? (
                        <Dialog
                            defaultShow={this.state.showErrorDialog}
                            onClick={() => this.hideErrorDialog()}
                        >
                            <article>
                                <h4>Sorry, something went wrong.</h4>
                                <p>{this.state.error.errorMessage}</p>
                                <Button onClick={() => this.hideErrorDialog()}>
                                    Close
                                </Button>
                            </article>
                        </Dialog>
                    ) : null}
                </div>
            </article>
        );
    }
}
