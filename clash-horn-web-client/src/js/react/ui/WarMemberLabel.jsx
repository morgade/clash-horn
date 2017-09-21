import React from 'react';
import Col from 'react-bootstrap/lib/Col';
import Row from 'react-bootstrap/lib/Row';

/**
 * WarMember label
 */
class WarMemberLabel extends React.Component {
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        let war = this.props.war;
        let position = this.props.position;
        
        console.log(position);
        
        return (
            <div className="wml">
                <Row>
                    <Col md={2} className="text-center">
                        <span className={`wml-th wml-th-${position.enemy.townhallLevel}`} />
                    </Col>
                    <Col md={10}>
                        <div className="wml-position pull-right">
                            {position.enemy.position}
                        </div>
                            
                        <h4>
                            {position.enemy.name}
                        </h4>
                        
                    </Col>
                </Row>
            </div>
        );
    }
};

WarMemberLabel.defaultProps = {
    war: null,
    position: null
};

export default WarMemberLabel;