import React from 'react';
import { connect } from 'react-redux';
import Table from 'react-bootstrap/lib/Table';
import ReactCSSTransitionGroup from 'react-addons-css-transition-group';

import CommentLine from './CommentLine.jsx';
import * as CommentsActions from '../../flux/actions/comments'

export class CommentTable extends React.Component {
    
    componentDidMount() {
        this.props.dispatch(CommentsActions.fetchComments());
    }
    
    render() {
        const lines = this.props.comments.list.map( c => (
            <CommentLine key={c.id} id={c.id} name={c.name} email={c.email} text={c.text} />
        ));
        
        return (
            <Table responsive striped hover>
                <caption>
                    Table of comments {' '}
                    <span className={this.props.comments.pendingFetch ? "loading-16" : ""} />
                </caption>
                <thead>
                  <tr>
                    <th>
                        Name
                    </th>
                    <th>e-Mail</th>
                    <th>Text</th>
                  </tr>
                </thead>
                    <ReactCSSTransitionGroup transitionName="fade-animation" transitionEnterTimeout={500} transitionLeaveTimeout={500}  component="tbody">
                      {lines}
                    </ReactCSSTransitionGroup>
              </Table>
        );
    }
};


export default 
    connect(
        store => ({ comments: store.comments })
    )(CommentTable);

