import React from 'react';
import CSSTransition  from 'react-transition-group/CSSTransition';
import TransitionGroup  from 'react-transition-group/TransitionGroup';
import { connect } from 'react-redux';
import WarQueueItem from './WarQueueItem.jsx'

/**
 * WarQueue label
 */
class WarQueue extends React.Component {
    
    render() {
        if (!this.props.war) {
            return null;
        }
        
        return (
            <div className="wp-queue">
                <TransitionGroup>
                {this.props.position.attackQueue.map( (plannedAttack, idx) =>
                    <CSSTransition key={plannedAttack.attacker} classNames="wp-queue-anim" timeout={300}>
                        <WarQueueItem war={this.props.war} position={this.props.position} queueIndex={idx} />
                    </CSSTransition>
                )}
                </TransitionGroup>
            </div>
        );
    }
};

WarQueue.defaultProps = {
    position: null,
    war: null
};

export default connect(
    (store) => ({    })
)(WarQueue);