import React from "react";
import { connect } from "react-redux";
import {
    getAllConflicts,
    punishConflict,
    resolveConflict
} from "../../../../Services/Conflict/conflictCompleteService";
import ConflictDetailView from "./ConflictDetailView/ConflictDetailView";

class ConflictView extends React.Component {
    componentWillMount() {
        getAllConflicts();
    }

    punishConf = async id => {
        await punishConflict(id);
        await getAllConflicts();
    };

    resolveConf = async id => {
        await resolveConflict(id);
        await getAllConflicts();
    };

    renderConflictList() {
        return (
            <div>
                {
                    this.props.conflictList.length === 0 ? <article>
                        <h4>There are currently no conflicts</h4>
                    </article>: null
                }
                {this.props.conflictList.map(conf => (
                    <ConflictDetailView
                        key={conf.id}
                        conflict={conf}
                        article={conf.inquiry.borrowArticle}
                        lender={conf.inquiry.borrowArticle.owner}
                        borrower={conf.inquiry.borrower}
                        getAllConflicts={this.getAllConflicts}
                        resolveConflict={this.resolveConf}
                        punishConflict={this.punishConf}
                    />
                ))}
            </div>
        );
    }

    render() {
        return <>{this.renderConflictList()}</>;
    }
}
const mapStateToProps = state => {
    return { conflictList: state.conflictstore.conflictList };
};

export default connect(mapStateToProps)(ConflictView);
