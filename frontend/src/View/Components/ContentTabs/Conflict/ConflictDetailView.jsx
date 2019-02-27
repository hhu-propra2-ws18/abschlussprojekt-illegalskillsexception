import React from "react"
import Button from "react-uwp/Button";

export default (props) => (
    <div className="grid-article-view">
        <article>
            <h3>{props.article.title}</h3>
            <p><span> Lender: {props.lender.username}</span></p>
            <p><span> Borrower: {props.borrower.username}</span></p>
            <h5>Status</h5>
            <p> {props.conflict.status}</p>
            <Button onClick={()=> props.resolveConflict(props.conflict.id)}>Resolve</Button>
            <Button onClick={()=> props.punishConflict(props.conflict.id)}>Punish</Button>
        </article>
    </div>
);