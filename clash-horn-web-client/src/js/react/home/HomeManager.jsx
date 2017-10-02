import React from 'react';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Form from 'react-bootstrap/lib/Form';
import ControlLabel from 'react-bootstrap/lib/ControlLabel';
import Button from 'react-bootstrap/lib/Button';
import Glyphicon from 'react-bootstrap/lib/Glyphicon';
import FormControl from 'react-bootstrap/lib/FormControl';
import FormGroup from 'react-bootstrap/lib/FormGroup';
import InputGroup from 'react-bootstrap/lib/InputGroup';
import ClanAccountInput from '../ui/ClanAccountInput.jsx';
import { Link } from 'react-router-dom';
import { connect, dispatch } from 'react-redux';
import { fetchClanAccount } from '../../flux/actions/clans';
import { notifySuccess } from '../../flux/actions/commons';

/**
 *  Home screen for clash-horn
 */
export class Home extends React.Component {
    
    constructor(props){
        super(props);
    }
    
    searchClanAccount(clanAccountId) {
        this.props.dispatch(fetchClanAccount(clanAccountId));
    }
    
    componentWillReceiveProps(nextProps) {
        if (nextProps.registeredClanAccount && nextProps.registeredClanAccount !== this.props.registeredClanAccount) {
            // Go to clan manager after registering account
            this.props.dispatch(notifySuccess('Redirecting to clan account'));
            this.props.history.push(`/${nextProps.registeredClanAccount.id}`);
        }
        
    }
    
    render() {
        return (
            <div>
                <Row>
                    <Col md={12}>
                        <h3>Welcome to Clash Horn</h3>
                        <p>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                            Cras elementum velit eget lorem dictum pellentesque. 
                            Quisque luctus est id quam finibus, ac pulvinar quam tincidunt. 
                            Cras ut pellentesque eros. Vestibulum id suscipit justo, eu ornare eros. 
                            Maecenas ut dui vulputate, efficitur purus sed, tristique nisl. 
                            Sed vehicula justo porta, pulvinar elit vitae, vehicula nulla. 
                            Duis lectus ante, tempor et erat at, volutpat suscipit enim. 
                            Cras vestibulum urna sed massa porttitor ullamcorper. Nulla quis odio 
                            consectetur, egestas ante eget, consequat neque. Vestibulum aliquet 
                            eu ipsum et tincidunt.
                        </p>
                    </Col>
                </Row>
                
                <Row>
                    <Col md={12}>
                        <p>If it´s your fisrt time here, <Link to="/register">Go to the register page</Link></p>
                    </Col>
                </Row>
                
                <Form>
                    <Row>
                        <Col md={12}>
                            <FormGroup controlId="clanTag">
                                <ControlLabel>
                                    Type your clan account id here:
                                </ControlLabel>
                                
                                <ClanAccountInput label="Find Account"
                                                  fetching={this.props.fetching['fetchClanAccount']} 
                                                  onAction={this.searchClanAccount.bind(this)} />
                            </FormGroup>
                        </Col>
                    </Row>
                    
                </Form>
            </div>
        );
    }
};

export default connect(
    store => ({ 
        registeredClanAccount: store.clans.clanAccount,
        fetching: store.clans.fetching
    })
)(Home);
