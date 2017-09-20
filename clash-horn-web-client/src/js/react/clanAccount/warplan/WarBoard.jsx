import React from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';


import ClanLabel from '../../ui/ClanLabel.jsx';
import WarMemberLabel from '../../ui/WarMemberLabel.jsx';


class WarBoard extends React.Component {
    
    render() {
        return (
            <div>
                <div>
                    <ClanLabel clan={this.props.war.clan} badgeAlignment="right" />
                    X
                    <ClanLabel clan={this.props.war.enemy} />
                </div>
                {this.props.war.positions.map( (p) => 
                    <Row key={p.member.tag}>
                        <Col md={6}>
                            <WarMemberLabel warMember={p.member} />
                        </Col>
                        <Col md={6}>
                            <WarMemberLabel warMember={p.enemy} />
                        </Col>
                    </Row>
                )}
            </div>
        );
    }
};

export default WarBoard
