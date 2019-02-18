import React from "react";
import ProcessItem from "./ProcessItem/ProcessItem";

const items = [
    {
        title: "Der Gerät 9000",
        status: "Open",
        returnDate: "24.02.2019",
        id: 1
    }
];

export default class ProcessesView extends React.Component {
    render() {
        return (
            <>
                <div id="testtext" className="grid-article-view">
                    {items.map(dataItem => (
                        <ProcessItem key={dataItem.id} data={dataItem} />
                    ))}
                </div>
            </>
        );
    }
}
