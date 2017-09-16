import React from 'react';
import Form from 'react-bootstrap/lib/Form';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import Button from 'react-bootstrap/lib/Button';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormControlStatic from 'react-bootstrap/lib/FormControlStatic';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import Row from 'react-bootstrap/lib/Row';
import CSSTransition  from 'react-transition-group/CSSTransition';

import ClanSearchInput from '../ui/ClanSearchInput.jsx'

import { connect, dispatch } from 'react-redux'
import { fetchClanData, setAccountClan } from '../../flux/actions/clans'


/**
 *  TODO: Break into smaller components
 */
export class RegisterManager extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {
            clanAccountId: null
        };
    }
    
    findClan(tag) {
        this.props.dispatch(fetchClanData(tag));
        this.setState({clanAccountId: this.generateClanAccountId() });
    }
    
    generateClanAccountId() {
        let s4 = () => Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
        return `${s4()}${s4()}-${s4()}-${s4()}-${s4()}-${s4()}${s4()}${s4()}`.toUpperCase();
    }
    
    createAccount() {
        this.props.dispatch(setAccountClan(this.props.clan));
        this.props.history.push(`/${this.state.clanAccountId}`);
    }
    
    render() {
        return (
            <div>
                <Row>
                    Welcome to Clash Horn. 
                </Row>
                
                <Form>
                    <Row>
                        <FormGroup controlId="clanTag">
                            <ControlLabel>
                                Type your clan tag to start managing it's wars.   {this.state.clanAccountId}
                            </ControlLabel>

                            <ClanSearchInput clan={this.props.clan} onFindClanRequested={this.findClan.bind(this)} />
                        </FormGroup>
                    </Row>
                    <CSSTransition in={this.props.clan!==null} classNames="fade" timeout={500}>
                        { this.props.clan ?
                            <Row>
                                <FormGroup controlId="clanName">
                                    <ControlLabel>
                                        Clan Name
                                    </ControlLabel>
                                    <FormControlStatic>
                                        {this.props.clan.name}
                                    </FormControlStatic>
                                </FormGroup>

                                <FormGroup controlId="clanDescription">
                                    <ControlLabel>
                                        Description
                                    </ControlLabel>
                                    <FormControlStatic>
                                        {this.props.clan.description}
                                    </FormControlStatic>
                                </FormGroup>

                                <FormGroup controlId="groupId">
                                    <ControlLabel>
                                        Confirm your clan account ID
                                    </ControlLabel>

                                    <InputGroup>
                                        <FormControl type="text" 
                                            value={this.state.clanAccountId} 
                                            onChange={(evt) => this.setState({clanAccountId: evt.target.value}) } />

                                        <InputGroup.Button>
                                            <Button bsStyle="primary" type="button" className="pull-right" onClick={this.createAccount.bind(this)}>Create Account</Button>
                                        </InputGroup.Button>
                                    </InputGroup>
                                </FormGroup>
                            </Row>
                        :
                            <div></div>
                        }
                    </CSSTransition>
                    
                </Form>
            </div>
        );
    }
};

export default connect(
    store => ({ 
        clan: store.clans.fetchedClan,
        clanAccountId: store.clans.clanAccountId
    })
)(RegisterManager);
