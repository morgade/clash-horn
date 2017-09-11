import React from 'react';
import { connect, dispatch } from 'react-redux'
import Button from 'react-bootstrap/lib/Button';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import Form from 'react-bootstrap/lib/Form';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';

import * as CommentActions from '../../flux/actions/comments'
import * as RouteActions from '../../flux/actions/routes'

class CommentForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            email: '',
            text: ''
        };
    }

    nameChanged(evt) {
        this.setState({name: evt.target.value});
    }
    
    emailChanged(evt) {
        this.setState({email: evt.target.value});
    }
    
    textChanged(evt) {
        this.setState({text: evt.target.value});
    }
    
    postComment() {
        this.props.dispatch(CommentActions.postComment({
               id: this.props.id==='new' ? null : this.props.id,
             name: this.state.name,
            email: this.state.email,
             text: this.state.text
        }));
    }
    
    deleteComment() {
        this.props.dispatch(CommentActions.deleteComment(this.state.id));
    }
    
    componentWillReceiveProps(nextProps) {
        if (nextProps.comments.listInvalidated) {
            this.props.dispatch(RouteActions.routeChange('/comment-crud'));
        }
        if (nextProps.comments.focused) {
            this.setState(nextProps.comments.focused);
        }
    }
    
    componentDidMount() {
        if (this.props.id && this.props.id!=='new') {
            this.props.dispatch(CommentActions.focusOrFetchComment(this.props.id));
        }
    }

    render() {
         return (
            <Form>
                <FormGroup controlId="name">
                    <ControlLabel>Name:</ControlLabel>
                    <FormControl type="text" 
                        value={this.state.name} 
                        placeholder="Type your name" 
                        disabled={this.props.comments.pendingFetch}
                        onChange={this.nameChanged.bind(this)} />
                </FormGroup>
                
                <FormGroup controlId="email">
                    <ControlLabel>e-Mail:</ControlLabel>
                    <FormControl type="text" 
                        value={this.state.email} 
                        placeholder="Type your email" 
                        disabled={this.props.comments.pendingFetch}
                        onChange={this.emailChanged.bind(this)} />
                </FormGroup>
                
                <FormGroup controlId="email">
                    <ControlLabel>Comment:</ControlLabel>
                    <FormControl type="text" 
                        value={this.state.text} 
                        componentClass="textarea" 
                        placeholder="Type your comment text"
                        disabled={this.props.comments.pendingFetch}
                        onChange={this.textChanged.bind(this)} />
                </FormGroup>
                
                <Button bsStyle="primary" onClick={this.postComment.bind(this)} disabled={this.props.comments.pendingFetch}>
                    {this.props.id==='new'? 'Post Comment' : 'Edit Comment'}
                </Button>
                
                {' '}
                {this.props.id!=='new'? 
                    <Button bsStyle="danger" onClick={this.deleteComment.bind(this)} disabled={this.props.comments.pendingFetch}>
                        Delete
                    </Button>
                    :
                    null
                }
                
                {' '}
                <span className={this.props.comments.pendingFetch ? "loading-16" : ""} />
            </Form>
        );
    }
};

export default connect(store => ({ comments: store.comments }))(CommentForm);