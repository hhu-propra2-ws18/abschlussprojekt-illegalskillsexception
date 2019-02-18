import React from "react";
import BorrowItemComponent from "./BorrowItemComponent/BorrowItemComponent";

const items = [
    {
        title: "Der Gerät 9000",
        dailyRate: "3,50 €",
        deposit: "500€",
        location: "Dönerbude um die Ecke"
    }
];

export default class BorrowView extends React.Component {
    render() {
        return (
            <>
                <div id="borrow-grid" className="grid-article-view">
                    {items.map(dataItem => (
                        <BorrowItemComponent
                            key={dataItem.title}
                            data={dataItem}
                        />
                    ))}
                </div>
            </>
        );
    }
}
