import Form from 'react-bootstrap/lib/Form';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import Button from 'react-bootstrap/lib/Button';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import React from 'react';
import { connect } from 'react-redux'

import * as routes from '../../flux/actions/routes'

export class GroupManager extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            clanTag: '#'
        };
    }
    
    clanTagChanged(evt) {
        var tag = evt.target.value.toUpperCase().replace(/#*/g,"");
        tag = "#" + tag;
        this.setState({clanTag: tag});
    }
    
    addClan() {
        alert('Not yet');
    }
    
    render() {
        return (
            <div>
                This is the group data management root component.
                Your group account id is <strong>{this.props.routeParams.gid}</strong> !
                        
                <Form>
                    <FormGroup controlId="groupId">
                        <ControlLabel>
                            Type a clan tag to add to your listed clans
                        </ControlLabel>
                        <InputGroup>
                            <FormControl type="text" 
                                value={this.state.clanTag} 
                                placeholder="Type a clan tag" 
                                onChange={this.clanTagChanged.bind(this)} />
                            <InputGroup.Button>
                                <Button bsStyle="primary" type="button" className="pull-right" onClick={this.addClan.bind(this)}>
                                    Register
                                </Button>
                            </InputGroup.Button>
                        </InputGroup>
                        
                    </FormGroup>
                </Form>
                
                <p><strong>Your clans:</strong></p>
                <p>Assigned clans component here</p>
                <p>An example: <a href={`#/${this.props.routeParams.gid}/2266374`}>TJF</a></p>
            </div>
        );
    }
};

export default connect()(GroupManager);
