import React from 'react';
import Form from 'react-bootstrap/lib/Form';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import Button from 'react-bootstrap/lib/Button';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormControlStatic from 'react-bootstrap/lib/FormControlStatic';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import CSSTransition  from 'react-transition-group/CSSTransition';

import ClanSearchInput from '../ui/ClanSearchInput.jsx';
import ClanLabel from '../ui/ClanLabel.jsx';
import CoCStatusAlert from '../ui/CoCStatusAlert.jsx';
import ClanAccountInput  from '../ui/ClanAccountInput.jsx';

import { connect, dispatch } from 'react-redux';
import { fetchClanData, registerClanAccount } from '../../flux/actions/clans';
import { notifySuccess } from '../../flux/actions/commons';


/**
 *  TODO: Break into smaller components
 */
export class RegisterManager extends React.Component {
    
    constructor(props) {
        super(props);
    }
    
    findClan(tag) {
        this.props.dispatch(fetchClanData(tag));
    }
    
    createAccount(accountId) {
        this.props.dispatch(registerClanAccount(this.props.clan.tag, accountId));
    }
    
    componentWillReceiveProps(nextProps) {
        if (nextProps.registeredClanAccount && nextProps.registeredClanAccount !== this.props.registeredClanAccount) {
            // Go to clan manager after registering account
            this.props.dispatch(notifySuccess('Your clan account has been succesfully created'));
            this.props.history.push(`/${nextProps.registeredClanAccount.id}`);
        }
    }
    
    generateClanAccountId() {
        let s4 = () => Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
        return `${s4()}${s4()}-${s4()}-${s4()}-${s4()}-${s4()}${s4()}${s4()}`.toUpperCase();
    }
    
    render() {
        return (
            <div>
                <Form>
                    <Row>
                        <Col md={12}>
                            <FormGroup controlId="clanTag">
                                <ControlLabel>
                                    Type your clan tag to start managing it's wars.  Examples: (#RUV9LQYP, #22PLRY2G)
                                </ControlLabel>

                                <ClanSearchInput clan={this.props.clan} onFindClanRequested={this.findClan.bind(this)} fetching={this.props.fetching['fetchClanDataRequest']} />
                                <CoCStatusAlert notFound={`Could not find a clan with the provided tag`} method="fetchClanData" />
                            </FormGroup>
                            <CSSTransition in={this.props.clan!==null} classNames="fade" timeout={500}>
                                { this.props.clan ?
                                <div>
                                        <FormGroup controlId="clanName">
                                            <ControlLabel>
                                                Clan Name
                                            </ControlLabel>

                                            <FormControlStatic>
                                                <ClanLabel clan={this.props.clan} />
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

                                            <ClanAccountInput 
                                                        label="Create Account"
                                                        initialValue={this.generateClanAccountId()}
                                                        fetching={this.props.fetching['registerClanAccount']} 
                                                        onAction={this.createAccount.bind(this)} />
                                        </FormGroup>
                                    </div>
                                :
                                    <div></div>
                                }
                            </CSSTransition>
                        </Col>
                    </Row>
                    
                </Form>
            </div>
        );
    }
};

export default connect(
    store => ({ 
        clan: store.clans.fetchedClan,
        registeredClanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(RegisterManager);
