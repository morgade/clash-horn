import React from 'react';
import Form from 'react-bootstrap/lib/Form';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import Button from 'react-bootstrap/lib/Button';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import { connect } from 'react-redux'

import * as routes from '../../flux/actions/routes'

/**
 *  TODO: Break into smaller components
 */
export class RegisterManager extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            groupId: ''
        };
    }
    
    groupIdChanged(evt) {
        this.setState({groupId: evt.target.value.toUpperCase()});
    }
    
    register(evt) {
        this.props.dispatch(routes.routeChange(`/${this.state.groupId}`));
    }
    
    render() {
        return (
            <div>
                This is the register management root component
                <Form>
                
                    <FormGroup controlId="groupId">
                        <ControlLabel>
                            Choose a secret ID to manage your war plans
                        </ControlLabel>

                        <InputGroup>
                            <FormControl type="text" 
                                value={this.state.groupId} 
                                placeholder="Type your account secret id" 
                                onChange={this.groupIdChanged.bind(this)} />
                            <InputGroup.Button>
                                <Button bsStyle="primary" type="button" className="pull-right" onClick={this.register.bind(this)}>
                                    Register
                                </Button>
                            </InputGroup.Button>
                        </InputGroup>
                        
                    </FormGroup>
                    
                </Form>
            </div>
        );
    }
};

export default connect()(RegisterManager);
