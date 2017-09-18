import React from 'react';
import Grid from 'react-bootstrap/lib/Grid';
import Row from 'react-bootstrap/lib/Row';
import Col from 'react-bootstrap/lib/Col';
import Image from 'react-bootstrap/lib/Image';

import th9 from '../../../img/th9-small.png';

class WarBoard extends React.Component {
    
    render() {
        return (
            <div>
                <div>
                    <span>
                        {this.props.war.clan.name}
                        <img src={this.props.war.clan.badgeUrls.small} />
                    </span>
                    X
                    <span>
                        <img src={this.props.war.opponent.badgeUrls.small} />
                        {this.props.war.opponent.name}
                    </span>
                </div>
                <Grid fluid>
                    <Row className="show-grid">
                        <Col xs={12} md={12}>
                            <span className="position">1</span>
                            <Image src={th9} rounded/>
                            <span className="villageName">DARK SLAYER</span>
                        </Col>
                    </Row>
                </Grid>
            </div>
        );
    }
};

export default WarBoard;

