import React from 'react';
import FormControl from 'react-bootstrap/lib/FormControl';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import Button from 'react-bootstrap/lib/Button';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';

import { fetchClanData } from '../../flux/actions/clans'

class ClanAccountRegisterInput extends React.Component {
    
    constructor(props){
        super(props);
        this.state = {
            clanAccountId: this.generateClanAccountId()
        };
    }
    
    generateClanAccountId() {
        let s4 = () => Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
        return `${s4()}${s4()}-${s4()}-${s4()}-${s4()}-${s4()}${s4()}${s4()}`.toUpperCase();
    }
    
    register() {
        this.props.onRegisterClanRequested(this.state.clanAccountId);
    }
    
    render() {
        return (
            <InputGroup>
    
                <FormControl type="text" 
                        value={this.state.clanAccountId} 
                        disabled={this.props.fetching}
                        onChange={(evt) => this.setState({clanAccountId: evt.target.value}) } />

                <InputGroup.Button>
                    <Button
                        bsStyle="primary"
                        type="button" 
                        disabled={this.props.fetching}
                        onClick={this.register.bind(this)}>
                            {!this.props.fetching ?
                                "Create Account"
                                :
                                <Glyphicon glyph="refresh" />
                            }
                    </Button>
                </InputGroup.Button>
                
            </InputGroup>
        );
    }
};

ClanAccountRegisterInput.defaultProps = {
    fetching: false,
    onRegisterClanRequested: () => {}
};

export default ClanAccountRegisterInput;