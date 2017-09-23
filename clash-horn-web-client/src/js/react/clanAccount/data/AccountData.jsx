import React from 'react';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';

import { connect } from 'react-redux';
import { Link } from 'react-router-dom';


export class ClanManager extends React.Component {
   
    render() {
        return (
            this.props.clanAccount ?
                <div className="ac-data">
                    <Row>
                        <Col md={1} className="ac-data-col-badge">
                            <Image src={this.props.clanAccount.clan.badge} />
                        </Col>
                        <Col md={11} className="ac-data-col-name">
                            <h2>{this.props.clanAccount.clan.name}</h2>
                        </Col>
                    </Row>
                    
                    <Row>
                        <Col md={12}>
                            <p>This is your clan account page. You clan build a war planning log by keeping this page bookmarked.</p>
                            <p>You can make this page more secure and easily accessible by authenticating here. (Coming soon)</p>
                            <p>Manage your clan's current war <Link to={`${this.props.clanAccount.id}/current`}>here</Link></p>
                        </Col>
                    </Row>
                </div>
            :
            null
        );
    }
};

export default connect(
    store => ({ 
        clanAccount: store.clans.clanAccount
    })
)(ClanManager);
