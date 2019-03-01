import React from "react";
import Button from "react-uwp/Button";

export default props => (
    <div className="grid-article-view">
        <article>
            <h3>{props.article.title}</h3>
            <p>
                <span className="underline">Lender: </span>
                {props.lender.username}
            </p>
            <p>
                <span className="underline">Borrower: </span>
                {props.borrower.username}
            </p>
            <h5>Status</h5>
            <p> {props.conflict.status}</p>
            <div className="two-buttons-bottom">
                <Button
                    onClick={() => props.resolveConflict(props.conflict.id)}
                >
                    Resolve
                </Button>
                <Button onClick={() => props.punishConflict(props.conflict.id)}>
                    Punish
                </Button>
            </div>
        </article>
    </div>
);
