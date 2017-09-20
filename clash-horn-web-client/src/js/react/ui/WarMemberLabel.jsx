import React from 'react';
import Image from 'react-bootstrap/lib/Image';

import th1 from '../../../img/th1-small.png';
import th2 from '../../../img/th2-small.png';
import th3 from '../../../img/th3-small.png';
import th4 from '../../../img/th4-small.png';
import th5 from '../../../img/th5-small.png';
import th6 from '../../../img/th6-small.png';
import th7 from '../../../img/th7-small.png';
import th8 from '../../../img/th8-small.png';
import th9 from '../../../img/th9-small.png';
import th10 from '../../../img/th10-small.png';
import th11 from '../../../img/th11-small.png';

/**
 * WarMember label
 */
class WarMemberLabel extends React.Component {
    
    render() {
        if (!this.props.warMember) {
            return null;
        }
        
        let thImgs = {'1':th1,'2':th2,'3':th3,'4':th4,'5':th5,'6':th6,'7':th7,'8':th8,'9':th9,'10':th10,'11':th11};
        
        return (
            <div className="war-member-label">
                <span className="position">
                    {this.props.warMember.position}
                </span>
                
                <Image src={thImgs[this.props.warMember.townhallLevel.toString()]} rounded/>
                
                <span className="villageName">
                    {this.props.warMember.name}
                </span>
            </div>
        );
    }
};

WarMemberLabel.defaultProps = {
    clan: null,
    badgeAlignment: 'left'
};

export default WarMemberLabel;