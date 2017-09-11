import React from 'react';
import {connect} from 'react-redux';
import * as RouteActions from '../../flux/actions/routes';

export class CommentLine extends React.Component {
    constructor(props) {
        super(props);
    }

    showCommentForm() {
        this.props.dispatch(RouteActions.routeChange(`/comment-crud/${this.props.id}`));
    }

    render() {
        return (
            <tr className="clickable" onClick={this.showCommentForm.bind(this)}>
                <td>{this.props.name}</td>
                <td>{this.props.email}</td>
                <td>{this.props.text}</td>
            </tr>
        );
    }
};

CommentLine.defaultProps = {
    id: '--',
    name: '--',
    email: '--',
    text: '--'
};

export default connect()(CommentLine);