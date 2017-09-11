import React from 'react';
import ReactCSSTransitionGroup from 'react-addons-css-transition-group';
import { connect } from 'react-redux'
import Button from 'react-bootstrap/lib/Button';

import * as CommentActions from '../../flux/actions/comments'
import * as RouteActions from '../../flux/actions/routes'
import CommentTable from './CommentTable.jsx';
import CommentForm from './CommentForm.jsx';

export class CommentCrud extends React.Component {
    
    newComment() {
        this.props.dispatch(CommentActions.focusComment(null));
        this.props.dispatch(RouteActions.routeChange('/comment-crud/new'));
    }

    render() {
        return (
            <div>
                {this.props.params.id ?
                
                    <CommentForm key="form" id={this.props.params.id} /> 
                    
                    :
                            
                    <div key="table" >
                        <Button onClick={this.newComment.bind(this)}>New Comment</Button>
                        <CommentTable />
                    </div>
                }
            </div>
        );
    }
};

export default connect()(CommentCrud);
